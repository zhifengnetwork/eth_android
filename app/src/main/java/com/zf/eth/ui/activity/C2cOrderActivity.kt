package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.base.BaseFragmentAdapter
import com.zf.eth.ui.fragment.c2c.AdvertFragment
import com.zf.eth.ui.fragment.c2c.AppealFragment
import com.zf.eth.ui.fragment.c2c.OrderFragment
import kotlinx.android.synthetic.main.activity_c2c_order.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class C2cOrderActivity:BaseActivity(){
    companion object {
        fun actionStart(context: Context?) {
            context?.startActivity(Intent(context, C2cOrderActivity::class.java))
        }
    }
    override fun initToolBar() {
        titleName.text="ETH/CNY"
    }
    override fun layoutId(): Int= R.layout.activity_c2c_order

    private val adapter by lazy { BaseFragmentAdapter(supportFragmentManager, listOf(OrderFragment.getInstance(),AdvertFragment.getInstance(),AppealFragment.getInstance()), arrayListOf("我的订单", "发布广告","我的申诉")) }
    override fun initData() {

    }

    override fun initView() {
        order_vp.adapter=adapter
        order_tab.setupWithViewPager(order_vp)
    }

    override fun initEvent() {

    }

    override fun start() {

    }

}