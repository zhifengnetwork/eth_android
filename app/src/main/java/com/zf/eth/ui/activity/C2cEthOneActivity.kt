package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.mvp.bean.MyOrderBean
import com.zf.eth.mvp.bean.MyOrderList
import com.zf.eth.mvp.contract.CancelOrderContract
import com.zf.eth.mvp.presenter.CancelOrderPresenter
import com.zf.eth.showToast
import kotlinx.android.synthetic.main.activity_c2c_eth1.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class C2cEthOneActivity : BaseActivity(), CancelOrderContract.View {
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

    companion object {
        fun actionStart(context: Context?, mData: MyOrderList) {
            val intent = Intent(context, C2cEthOneActivity::class.java)
            intent.putExtra("mData", mData)
            context?.startActivity(intent)

        }
    }

    override fun initToolBar() {
        if (data?.type == "1") titleName.text = "卖出ETH" else titleName.text = "买入ETH"

        titleName.textSize = 22f
        titleName.paint.isFakeBoldText = true
        titleBackground.setBackgroundResource(R.drawable.bg1)

        backLayout.setOnClickListener {
            finish()
        }
    }

    override fun layoutId(): Int = R.layout.activity_c2c_eth1

    private var data: MyOrderList? = null

    private val presenter by lazy { CancelOrderPresenter() }

    override fun initData() {
        data = intent.getSerializableExtra("mData") as MyOrderList
    }

    override fun initView() {

        presenter.attachView(this)

        order_id.text = data?.id
        order_openid.text = data?.nickname
        order_price.text = data?.price
        order_sum.text = data?.trx
        order_money.text = data?.money
    }

    override fun initEvent() {
        cancel_btn.setOnClickListener {
            presenter.requesCancelOrder(order_id.text.toString())
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun start() {

    }

}