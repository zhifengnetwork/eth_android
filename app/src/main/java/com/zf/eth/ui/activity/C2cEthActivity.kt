package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import kotlinx.android.synthetic.main.layout_toolbar.*

class C2cEthActivity:BaseActivity(){
    companion object {
        fun actionStart(context: Context?) {
            context?.startActivity(Intent(context, C2cEthActivity::class.java))
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

    override fun layoutId(): Int =R.layout.activity_c2c_eth

    override fun initData() {

    }

    override fun initView() {

    }

    override fun initEvent() {

    }

    override fun start() {

    }

}