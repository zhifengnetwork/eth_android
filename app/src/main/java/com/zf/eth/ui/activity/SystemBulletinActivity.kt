package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.ui.adapter.SystemBulletinPagerAdapter
import kotlinx.android.synthetic.main.activity_system_bulletin.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class SystemBulletinActivity : BaseActivity() {
    companion object {
        fun actionStart(context: Context?) {
            context?.startActivity(Intent(context, SystemBulletinActivity::class.java))
        }
    }

    override fun initToolBar() {
        titleName.text = "平台公告"
    }

    override fun layoutId(): Int = R.layout.activity_system_bulletin

    private val title = arrayListOf("全部分类", "新手训练营")

    private val adapter by lazy { SystemBulletinPagerAdapter(supportFragmentManager, title) }

    override fun initData() {

    }

    override fun initView() {

        bulletin_vp.adapter = adapter
        bulletin_tab.setupWithViewPager(bulletin_vp)

    }

    override fun initEvent() {

    }

    override fun start() {

    }

}