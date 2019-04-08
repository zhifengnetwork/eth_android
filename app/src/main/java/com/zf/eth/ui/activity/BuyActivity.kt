package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import android.view.View
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import kotlinx.android.synthetic.main.activity_buy.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * 账户激活 投资购买
 */
class BuyActivity : BaseActivity() {

    companion object {
        fun actionStart(context: Context?) {
            context?.startActivity(Intent(context, BuyActivity::class.java))
        }
    }

    override fun initToolBar() {
        backLayout.setOnClickListener { finish() }
        titleName.text = "投资购买"
    }

    override fun layoutId(): Int = R.layout.activity_buy

    override fun initData() {
    }

    override fun initView() {
    }

    override fun initEvent() {
        buy.setOnClickListener {
            detailLayout.visibility = View.VISIBLE
            buy.visibility = View.GONE
        }
    }

    override fun start() {
    }
}