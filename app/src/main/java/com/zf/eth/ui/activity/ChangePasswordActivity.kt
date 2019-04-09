package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import kotlinx.android.synthetic.main.layout_toolbar.*

class ChangePasswordActivity:BaseActivity(){

    companion object {
        fun actionStart(context: Context?){
            context?.startActivity(Intent(context,ChangePasswordActivity::class.java))
        }
    }
    override fun initToolBar() {
        titleName.text="修改密码"
    }

    override fun layoutId(): Int = R.layout.activity_change_password

    override fun initData() {

    }

    override fun initView() {

    }

    override fun initEvent() {

    }

    override fun start() {

    }

}