package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.mvp.bean.OrderDetailBean
import com.zf.eth.mvp.contract.CancelOrderContract
import com.zf.eth.mvp.presenter.CancelOrderPresenter
import com.zf.eth.showToast
import kotlinx.android.synthetic.main.activity_c2c_eth1.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class C2cEthOneActivity : BaseActivity(), CancelOrderContract.View {
    override fun getOrderDetail(bean: OrderDetailBean) {
        mData = bean
        dataView()
    }

    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
    }

    override fun CancelOrderSuccess(msg: String) {
        showToast(msg)
        finish()
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

    private var id = ""

    companion object {
        fun actionStart(context: Context?, id: String) {
            val intent = Intent(context, C2cEthOneActivity::class.java)
            intent.putExtra("id", id)
            context?.startActivity(intent)

        }
    }

    override fun initToolBar() {

        titleName.textSize = 22f
        titleName.paint.isFakeBoldText = true
        titleBackground.setBackgroundResource(R.drawable.bg1)

        backLayout.setOnClickListener {
            finish()
        }
    }

    override fun layoutId(): Int = R.layout.activity_c2c_eth1

    private var mData: OrderDetailBean? = null
    private val presenter by lazy { CancelOrderPresenter() }

    override fun initData() {
        id = intent.getStringExtra("id")

    }

    override fun initView() {
        presenter.attachView(this)

    }

    override fun initEvent() {
        cancel_btn.setOnClickListener {
            presenter.requestCancelOrder(order_id.text.toString())
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun start() {
        presenter.requestOrderDetail(id)
    }

    private fun dataView() {
        order_id.text = mData?.list?.id
        order_openid.text = mData?.list?.mobile
        order_price.text = mData?.list?.price
        order_sum.text = mData?.list?.trx
        order_money.text = mData?.list?.money
        if (mData?.list?.type == "0"){
            titleName.text = "卖出ETH"
            order_Label.text = "待收款："
        } else{
            titleName.text = "买入ETH"
            order_Label.text = "待付款："
        }

        // order_id.text = data?.id
//        order_openid.text = data?.nickname
//        order_price.text = data?.price
//        order_sum.text = data?.trx
//        order_money.text = data?.money
    }
}