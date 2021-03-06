package com.zf.eth.ui.fragment

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.zf.eth.GlideApp
import com.zf.eth.R
import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseFragment
import com.zf.eth.livedata.UserInfoLiveData
import com.zf.eth.mvp.bean.UserInfoBean
import com.zf.eth.mvp.contract.LogOutContract
import com.zf.eth.mvp.contract.UserInfoContract
import com.zf.eth.mvp.presenter.LogOutPresenter
import com.zf.eth.mvp.presenter.UserInfoPresenter
import com.zf.eth.showToast
import com.zf.eth.ui.activity.*
import com.zf.eth.utils.Preference
import com.zf.eth.utils.bus.RxBus
import com.zf.eth.view.dialog.ContactDialog
import com.zf.eth.view.dialog.LogOutDialog
import kotlinx.android.synthetic.main.fragment_me.*
import kotlinx.android.synthetic.main.layout_me.*


class MeFragment : BaseFragment(), LogOutContract.View, UserInfoContract.View {

    override fun setUserInfo(bean: UserInfoBean) {
        showToast("退出成功")
        UserInfoLiveData.value = bean
//        val intent = Intent(context, MainActivity::class.java)
//            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
//        startActivity(intent)

        if (bean.member.type == "2" || bean.member.suoding == "1") {
            MainActivity.actionStart(context, 1)
        } else {
            MainActivity.actionStart(context, 3)
        }


    }

    override fun setNotLogin() {
    }

    private val infoPresenter by lazy { UserInfoPresenter() }

    //退出机制
    override fun setLogOut() {
        infoPresenter.requestUserInfo()
    }

    override fun showLoading() {
        showLoadingDialog()
    }

    override fun dismissLoading() {
        dismissLoadingDialog()
    }

    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
    }

    companion object {
        fun getInstance(): MeFragment {
            return MeFragment()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_me


    override fun initView() {
        infoPresenter.attachView(this)
        presenter.attachView(this)
    }

    //退出机制
    private val presenter by lazy { LogOutPresenter() }

    override fun lazyLoad() {
        UserInfoLiveData.observe(this, Observer { userInfo ->
            userInfo?.apply {
                if (member.avatar.isNotEmpty()) {
                    GlideApp.with(context!!).asBitmap()
                        .load(member.avatar)
                        .error(R.drawable.headportrait) //加载失败占位图
                        .into(me_img)
                }
                nickName.text = member.nickname
                vipId.text = "会员ID: ${member.id}"
                vipLevel.text = "会员等级: ${huiyuanlevel.levelname1 ?: "暂无"}"
                martLevel.text = "市场等级: ${huiyuanlevel.levelname3 ?: "暂无"}"
                ifActive.text = when (member.type) {
                    "0" -> "未激活"
                    "1" -> "已激活"
                    "2" -> "已锁户"
                    else -> ""
                }
                singOut.visibility = if (member.type == "2") View.GONE else View.VISIBLE
            }
        })

        if (userId.isEmpty()) {
            val intent = Intent(context, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
    }

    private val userId by Preference(UriConstant.USER_ID, "")

    override fun initEvent() {
        RxBus.getDefault().subscribe<String>(this, UriConstant.USER_LEVEL) {
            infoPresenter.requestUserLevel()
        }
        //退出登录
        logOut.setOnClickListener {
            val builder = AlertDialog.Builder(context!!)
            builder.setTitle("提示")
                .setMessage("当前已登录，确定要退出？")
                .setNegativeButton("取消") { _, _ -> }
                .setPositiveButton("确定") { _, _ ->
                    Preference.clearPreference(UriConstant.USER_ID)
                    UserInfoLiveData.value = null
                    val intent = Intent(context, LoginActivity::class.java)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
                .show()
        }

        me_img.setOnClickListener {
            if (userId.isNotEmpty()) {
                InfoActivity.actionStart(context)
            } else {
                LoginActivity.actionStart(context)
            }
        }
        //支付管理
        payManager.setOnClickListener {
            if (UserInfoLiveData.value?.member?.type == "2" || UserInfoLiveData.value?.member?.suoding == "1") {
                showToast("该账号已锁户！")
                return@setOnClickListener
            }
            PaymentActivity.actionStart(context)
        }
        //钱包地址
        payAddress.setOnClickListener {
            if (UserInfoLiveData.value?.member?.type == "2" || UserInfoLiveData.value?.member?.suoding == "1") {
                showToast("该账号已锁户！")
                return@setOnClickListener
            }
            WalletAddressActivity.actionStart(context)
        }
        //我的邀请
        invite.setOnClickListener {
            if (UserInfoLiveData.value?.member?.type == "2" || UserInfoLiveData.value?.member?.suoding == "1") {
                showToast("该账号已锁户！")
                return@setOnClickListener
            }
            InviteActivity.actionStart(context)
        }
        //修改密码
        changePwd.setOnClickListener {
            if (UserInfoLiveData.value?.member?.type == "2" || UserInfoLiveData.value?.member?.suoding == "1") {
                showToast("该账号已锁户！")
                return@setOnClickListener
            }
            ChangePasswordActivity.actionStart(context)
        }
        //平台公告
        bulletin.setOnClickListener {
            if (UserInfoLiveData.value?.member?.type == "2" || UserInfoLiveData.value?.member?.suoding == "1") {
                showToast("该账号已锁户！")
                return@setOnClickListener
            }
            SystemBulletinActivity.actionStart(context)
        }
        //联系客服
        contact.setOnClickListener {
            ContactDialog.showDialog(
                childFragmentManager,
                UserInfoLiveData.value?.kefu?.kefufile,
                UserInfoLiveData.value?.kefu?.wxkffile
            )
        }
        //退出机制
        singOut.setOnClickListener {
            UserInfoLiveData.value?.apply {
                LogOutDialog.showDialog(childFragmentManager, arr2.money ?: "0.00")
                    .onConfirmListener = {
                    presenter.requestLogOut(it)
                }
            }
        }

//        downLoad.setOnClickListener {
//            DownloadActivity.actionStart(context)
//        }

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
        infoPresenter.detachView()
    }

}