package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.base.BaseFragmentAdapter
import com.zf.eth.ui.fragment.game.PourFragment
import com.zf.eth.ui.fragment.game.RecordFragment
import com.zf.eth.ui.fragment.game.WinFragment
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class GameActivity : BaseActivity() {

    override fun initToolBar() {
        backLayout.setOnClickListener { finish() }
        titleName.text = "3D游戏"
    }

    companion object {
        fun actionStart(context: Context?) {
            context?.startActivity(Intent(context, GameActivity::class.java))
        }
    }

    override fun layoutId(): Int = R.layout.activity_game

    private val fragments = ArrayList<Fragment>()
    private val titles = arrayListOf("3D下注", "押注记录", "中奖记录")
    private val adapter by lazy { BaseFragmentAdapter(supportFragmentManager, fragments, titles) }

    override fun initView() {
        fragments.clear()
        fragments.add(PourFragment.getInstance())
        fragments.add(RecordFragment.getInstance())
        fragments.add(WinFragment.getInstance())
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 3
        tabLayout.setViewPager(viewPager)
    }

    override fun initData() {
    }


    override fun initEvent() {
    }

    override fun start() {
    }
}