package com.zf.eth.ui.fragment

import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.zf.eth.R
import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseFragment
import com.zf.eth.livedata.UserInfoLiveData
import com.zf.eth.ui.activity.*
import com.zf.eth.ui.adapter.MeMenuAdapter
import com.zf.eth.utils.GlideUtils
import com.zf.eth.utils.Preference
import kotlinx.android.synthetic.main.fragment_me.*


class MeFragment : BaseFragment() {

    companion object {
        fun getInstance(): MeFragment {
            return MeFragment()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_me


    private val title1 = arrayListOf("支付管理", "钱包地址", "C2C订单", "修改密码")
    private val img1 = arrayListOf(
            R.drawable.zhifu,
            R.drawable.qianbao,
            R.drawable.c2c_me,
            R.drawable.xiugaimima,
            R.drawable.yaoqing,
            R.drawable.gonggao,
            R.drawable.keuf,
            R.drawable.touzi,
            R.drawable.appicon3_16
    )
    private val title2 = arrayListOf("我的邀请", "平台公告", "联系客服", "退出机制", "下载APP")
    private val img2 =
            arrayListOf(R.drawable.yaoqing, R.drawable.gonggao, R.drawable.keuf, R.drawable.touzi, R.drawable.appicon3_16)

    private val adapter1 by lazy { MeMenuAdapter(context, title1, img1) }
    private val adapter2 by lazy { MeMenuAdapter(context, title2, img2) }

    override fun initView() {
        //第一个列表菜单
        me_oneRelView.layoutManager = LinearLayoutManager(context)
        me_oneRelView.adapter = adapter1
        //添加自定义颜色分割线
        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        divider.setDrawable(ContextCompat.getDrawable(context!!, R.drawable.shape_custom_divider)!!)

        me_oneRelView.addItemDecoration(divider)

        //第二个列表菜单
        me_twoRelView.layoutManager = LinearLayoutManager(context)
        me_twoRelView.adapter = adapter2
        //添加自定义颜色分割线
        me_twoRelView.addItemDecoration(divider)


    }

    override fun lazyLoad() {
        UserInfoLiveData.observe(this, Observer { userInfo ->
            userInfo?.apply {
                GlideUtils.loadUrlImage(context, member.avatar, me_img)
                nickName.text = member.nickname
                vipId.text = "会员ID: ${member.id}"
                vipLevel.text = "会员等级: ${huiyuanlevel.levelname1 ?: "暂无"}"
                martLevel.text = "市场等级: ${huiyuanlevel.levelname3 ?: "暂无"}"
                ifActive.text = when (member.type) {
                    "0" -> "未激活"
                    "1" -> "已激活"
                    else -> "已锁户"
                }
            }
        })
    }

    private val userId by Preference(UriConstant.USER_ID, "")

    override fun initEvent() {

        logOut.setOnClickListener {
            //清空用户信息
            //清空userId openId
            Preference.clearPreference(UriConstant.USER_ID)
            UserInfoLiveData.value = null

            //清楚所有任务
            val intent = Intent(context, LoginActivity::class.java)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        me_img.setOnClickListener {
            if (userId.isNotEmpty()) {
                InfoActivity.actionStart(context)
            } else {
                LoginActivity.actionStart(context)
            }
        }

//        /**第一个列表适配器点击事件*/
        adapter1.setOnItemClickListener(object : MeMenuAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                when (position) {
                    0 -> PaymentActivity.actionStart(context)
                    1 -> WalletAddressActivity.actionStart(context)
                    3 -> ChangePasswordActivity.actionStart(context)
                }
            }

        })
        /**第二个列表适配器点击事件*/
        adapter2.setOnItemClickListener(object : MeMenuAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                when (position) {
                    1 -> SystemBulletinActivity.actionStart(context)
                    4 -> DownloadActivity.actionStart(context)
                }

            }

        })
    }

}