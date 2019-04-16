package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import android.view.View
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.mvp.bean.MyOrderBean
import kotlinx.android.synthetic.main.activity_c2c_eth2.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class C2cEthTwoActivity:BaseActivity(){
    companion object {
        fun actionStart(context: Context?, mData: MyOrderBean) {
            val intent = Intent(context, C2cEthTwoActivity::class.java)
            intent.putExtra("mData", mData)
            context?.startActivity(intent)

        }
    }
    override fun initToolBar() {
        if (mData?.type=="1") titleName.text="买入ETH" else titleName.text="卖出ETH"
        titleName.textSize=22f
        titleName.paint.isFakeBoldText = true
        titleBackground.setBackgroundResource(R.drawable.bg1)
        backLayout.setOnClickListener {
            finish()
        }
    }

    override fun layoutId(): Int= R.layout.activity_c2c_eth2

    private var mData:MyOrderBean?=null
    override fun initData() {
        mData=intent.getSerializableExtra("mData") as MyOrderBean
    }

    override fun initView() {
        //订单号
        order_id.text=mData?.id
        //挂卖人
        order_openid.text=mData?.nickname
        //挂卖单价
        order_price.text=mData?.price
        //挂卖数量
        order_sum.text=mData?.trx
        //代收款
        order_money.text=mData?.money
        //收款人
        payee.text=mData?.nickname2
        //支付凭证

        //交易完成此页面隐藏确认收款按钮
        if(mData?.status=="2") receivables.visibility=View.GONE
        //交易失败此页面隐藏两个按钮
        if (mData?.status=="3") btn_ly.visibility=View.GONE
    }

    override fun initEvent() {

    }

    override fun start() {

    }
}
