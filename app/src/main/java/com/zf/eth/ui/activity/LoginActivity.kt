package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import com.zf.eth.R
import com.zf.eth.base.BaseActivity

class LoginActivity : BaseActivity() {

    companion object {
        fun actionStart(context: Context?) {
            context?.startActivity(Intent(context, LoginActivity::class.java))
        }
    }

    override fun initToolBar() {

    }

    override fun layoutId(): Int = R.layout.activity_login

    override fun initData() {
    }

    override fun initView() {
    }

    override fun initEvent() {
    }

    override fun start() {
    }
}