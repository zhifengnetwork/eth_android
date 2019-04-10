package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity() {

    companion object {
        fun actionStart(context: Context?) {
            context?.startActivity(Intent(context, RegisterActivity::class.java))
        }
    }

    override fun initToolBar() {
    }

    override fun layoutId(): Int = R.layout.activity_register

    override fun initData() {
    }

    override fun initView() {
    }

    override fun initEvent() {

        findPwd.setOnClickListener {
            FindPwdActivity.actionStart(this)
        }

        login.setOnClickListener {
            LoginActivity.actionStart(this)
        }
    }

    override fun start() {
    }
}