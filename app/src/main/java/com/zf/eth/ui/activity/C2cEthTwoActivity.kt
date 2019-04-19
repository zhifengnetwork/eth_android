package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import android.view.View
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.mvp.bean.MyOrderList
import com.zf.eth.mvp.contract.ConfirmOrderContrect
import com.zf.eth.mvp.presenter.ConfirmOrderPresenter
import com.zf.eth.showToast
import kotlinx.android.synthetic.main.activity_c2c_eth2.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class C2cEthTwoActivity : BaseActivity(), ConfirmOrderContrect.View {
    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
    }

    override fun setConfirmOrderSuccess(msg: String) {
        showToast(msg)
        finish()
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

    companion object {
        fun actionStart(context: Context?, mData: MyOrderList) {
            val intent = Intent(context, C2cEthTwoActivity::class.java)
            intent.putExtra("mData", mData)
            context?.startActivity(intent)

        }
    }

    override fun initToolBar() {
        if (mData?.type == "1") titleName.text = "买入ETH" else titleName.text = "卖出ETH"

        if (mData?.status == "3") {
            if (mData?.type == "1") titleName.text = "卖出ETH" else titleName.text = "买入ETH"
        }

        titleName.textSize = 22f
        titleName.paint.isFakeBoldText = true
        titleBackground.setBackgroundResource(R.drawable.bg1)
        backLayout.setOnClickListener {
            finish()
        }
    }

    override fun layoutId(): Int = R.layout.activity_c2c_eth2

    private var mData: MyOrderList? = null

    private val presenter by lazy { ConfirmOrderPresenter() }

    override fun initData() {
        mData = intent.getSerializableExtra("mData") as MyOrderList
    }

    override fun initView() {
        presenter.attachView(this)

        if (mData?.type == "1") openid_name.text = "挂 卖 人" else openid_name.text = "挂 买 人"
        //订单号
        order_id.text = mData?.id
        //挂卖人
        order_openid.text = mData?.nickname
        //挂卖单价
        order_price.text = mData?.price
        //挂卖数量
        order_sum.text = mData?.trx
        //代收款
        order_money.text = mData?.money
        //收款人
        payee.text = mData?.nickname2
        //支付凭证

        /**交易完成此页面隐藏确认收款按钮，修改布局文字*/
        if (mData?.status == "2") {
            if (mData?.type == "1") {
                order_openid.text = mData?.nickname
                payee.text = mData?.nickname2
                payee_name.text = "付 款 人："
            } else {
                order_openid.text = mData?.nickname2
                payee.text = mData?.nickname
                payee_name.text = "收 款 人："
            }
            confirm_btn.visibility = View.GONE
        }
        /**交易失败此页面隐藏两个按钮,修改布局的文字*/
        if (mData?.status == "3") {
            if (mData?.type == "0") {
                openid_name.text = "挂 买 人："
                money_name.text = "待 收 款："
                payee_name.text = "收 款 人："
                //挂卖人
                order_openid.text = mData?.nickname2
                //收款人
                payee.text = mData?.nickname
            } else {
                openid_name.text = "挂 卖 人："
                money_name.text = "待 收 款："
                payee_name.text = "付 款 人："
                //挂卖人
                order_openid.text = mData?.nickname
                //收款人
                payee.text = mData?.nickname2
            }
            btn_ly.visibility = View.GONE
        }
    }

    override fun initEvent() {
        //申诉
        appeal_btn.setOnClickListener {
            C2cComplainActivity.actionStart(this, mData?.id)
        }
        //确认收款
        confirm_btn.setOnClickListener {
            presenter.requestConfirmOrder(mData?.id ?: "", "1")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun start() {
    }
}
