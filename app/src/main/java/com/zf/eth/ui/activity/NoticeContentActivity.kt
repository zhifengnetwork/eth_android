package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zzhoujay.richtext.RichText
import kotlinx.android.synthetic.main.activity_notice_content.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class NoticeContentActivity : BaseActivity() {

    private var mDesc = ""

    companion object {
        fun actionStart(context: Context?, desc: String) {
            val intent = Intent(context, NoticeContentActivity::class.java)
            intent.putExtra("desc", desc)
            context?.startActivity(intent)
        }
    }

    override fun initData() {
        mDesc = intent.getStringExtra("desc")
    }

    override fun start() {
    }

    override fun layoutId(): Int = R.layout.activity_notice_content


    override fun initView() {
        RichText.initCacheDir(this)
        RichText.debugMode = true
        RichText.fromHtml(mDesc).into(textView)
    }

    override fun onDestroy() {
        super.onDestroy()
        RichText.clear(this)
    }

    override fun initToolBar() {
        backLayout.setOnClickListener { finish() }
        titleName.text = "详细内容"
    }

    override fun initEvent() {

    }


}