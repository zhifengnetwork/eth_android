package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import kotlinx.android.synthetic.main.activity_voting.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * 复投：复投账户复投
 *       自由钱包复投
 */
class VotingActivity : BaseActivity() {

    companion object {
        const val FREE_WALLET = "FREE_WALLET" //自由钱包
        const val RE_DELIVER = "RE_DELIVER" //复投账户
        fun actionStart(context: Context, type: String) {
            val intent = Intent(context, VotingActivity::class.java)
            intent.putExtra("type", type)
            context.startActivity(intent)
        }
    }

    private var mType = ""

    override fun initToolBar() {
        backLayout.setOnClickListener { finish() }
        titleName.text = "复投"

    }

    override fun layoutId(): Int = R.layout.activity_voting

    override fun initData() {
        mType = intent.getStringExtra("type")
    }

    override fun initView() {
        accountType.text = if (mType == FREE_WALLET) "自由钱包" else "复投账户"
    }

    override fun initEvent() {
    }

    override fun start() {
    }
}