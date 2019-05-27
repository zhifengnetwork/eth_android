package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import com.zf.eth.R
import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseActivity
import com.zf.eth.mvp.bean.RePayInfoBean
import com.zf.eth.mvp.contract.RePayContract
import com.zf.eth.mvp.presenter.RePayPresenter
import com.zf.eth.showToast
import com.zf.eth.utils.PriceInputFilter
import com.zf.eth.utils.bus.RxBus
import kotlinx.android.synthetic.main.activity_voting.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * 复投：复投账户复投
 *       自由钱包复投
 */
class VotingActivity : BaseActivity(), RePayContract.View {

    //获取复投信息
    override fun setRePayInfo(bean: RePayInfoBean) {
        currentPrice.text = bean.credit1
        money.text = if (mType == FREE_WALLET) bean.credit2 else bean.credit4
    }

    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
    }

    override fun setRePay() {
        showToast("购买成功")
        //购买成功请求用户信息 判断是否解锁
        RxBus.getDefault().post(UriConstant.USER_SWITCH, UriConstant.USER_SWITCH)
        finish()
    }

    override fun showLoading() {
        showLoadingDialog()
    }

    override fun dismissLoading() {
        dismissLoadingDialog()
    }

    companion object {
        const val FREE_WALLET = "FREE_WALLET" //自由钱包
        const val RE_DELIVER = "RE_DELIVER" //复投账户
        fun actionStart(context: Context?, type: String) {
            val intent = Intent(context, VotingActivity::class.java)
            intent.putExtra("type", type)
            context?.startActivity(intent)
        }
    }

    private var mType = ""

    override fun initToolBar() {
        backLayout.setOnClickListener { finish() }
        titleName.text = "复投"

    }

    override fun layoutId(): Int = R.layout.activity_voting

    private val presenter by lazy { RePayPresenter() }

    override fun initData() {
        mType = intent.getStringExtra("type")
    }

    override fun initView() {
        presenter.attachView(this)
        accountType.text = if (mType == FREE_WALLET) "自由钱包" else "复投账户"

        price.filters = arrayOf(PriceInputFilter())
    }

    override fun initEvent() {
        confirm.setOnClickListener {
            if (TextUtils.isEmpty(price.text)) {
                showToast("请输入金额")
            } else {
                presenter.requestRePay(price.text.toString(), if (mType == FREE_WALLET) "2" else "4")
            }
        }
    }

    override fun start() {
        presenter.requestRePayInfo()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

}