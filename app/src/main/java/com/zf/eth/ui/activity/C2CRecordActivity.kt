package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.ui.adapter.C2cRecordAdapter
import kotlinx.android.synthetic.main.activity_c2c_record.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * C2C交易记录
 */
class C2CRecordActivity : BaseActivity() {

    companion object {
        fun actionStart(context: Context?) {
            context?.startActivity(Intent(context, C2CRecordActivity::class.java))
        }
    }

    override fun initToolBar() {
        backLayout.setOnClickListener { finish() }
        titleName.text = "C2C交易记录"
    }

    override fun layoutId(): Int = R.layout.activity_c2c_record

    override fun initData() {
    }

    private val adapter by lazy { C2cRecordAdapter(this) }

    override fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun initEvent() {
    }

    override fun start() {
    }
}