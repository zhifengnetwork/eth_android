package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.mvp.bean.MyOrderBean
import kotlinx.android.synthetic.main.activity_c2c_eth1.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class C2cEthOneActivity:BaseActivity(){
    companion object {
        fun actionStart(context: Context?,mData: MyOrderBean) {
            val intent = Intent(context, C2cEthOneActivity::class.java)
            intent.putExtra("mData", mData)
            context?.startActivity(intent)

        }
    }
    override fun initToolBar() {
        titleName.text="卖出ETH"
        titleName.textSize=22f
        titleName.paint.isFakeBoldText = true
        titleBackground.setBackgroundResource(R.drawable.bg1)

        backLayout.setOnClickListener {
            finish()
        }
    }

    override fun layoutId(): Int =R.layout.activity_c2c_eth1

    private var data:MyOrderBean?=null

    override fun initData() {
        data=intent.getSerializableExtra("mData") as MyOrderBean
    }

    override fun initView() {
        order_id.text=data?.id
        order_openid.text=data?.nickname
        order_price.text=data?.price
        order_sum.text=data?.trx
        order_money.text=data?.money
    }

    override fun initEvent() {

    }

    override fun start() {

    }

}