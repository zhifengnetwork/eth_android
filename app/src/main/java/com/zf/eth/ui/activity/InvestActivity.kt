package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.ui.adapter.InvestAdapter
import kotlinx.android.synthetic.main.activity_invest.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * 投资记录
 */
class InvestActivity : BaseActivity() {

    override fun initToolBar() {
        backLayout.setOnClickListener { finish() }
        titleName.text = "投资记录"
    }

    companion object {
        fun actionStart(context: Context?) {
            context?.startActivity(Intent(context, InvestActivity::class.java))
        }
    }

    override fun layoutId(): Int = R.layout.activity_invest

    override fun initData() {
    }

    private val investAdapter by lazy { InvestAdapter(this) }

    override fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = investAdapter
    }

    override fun initEvent() {
    }

    override fun start() {
    }
}