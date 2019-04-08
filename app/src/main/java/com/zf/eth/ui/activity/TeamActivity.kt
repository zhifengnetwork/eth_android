package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.ui.adapter.TeamAdapter
import kotlinx.android.synthetic.main.activity_team.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * 团队 ->下级明细
 */
class TeamActivity : BaseActivity() {

    companion object {
        fun actionStart(context: Context?) {
            context?.startActivity(Intent(context, TeamActivity::class.java))
        }
    }

    override fun initToolBar() {
        backLayout.setOnClickListener { finish() }
        titleName.text = "下级明细"
    }

    override fun layoutId(): Int = R.layout.activity_team

    override fun initData() {
    }

    private val adapter by lazy { TeamAdapter(this) }

    override fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun initEvent() {
    }

    override fun start() {
    }
}