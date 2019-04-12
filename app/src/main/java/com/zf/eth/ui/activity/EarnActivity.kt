package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.base.BaseFragmentAdapter
import com.zf.eth.ui.fragment.earn.EarnFragment
import kotlinx.android.synthetic.main.activity_earn.*

/**
 * 总收益 和 今日收益
 */
class EarnActivity : BaseActivity() {

    companion object {
        fun actionStart(context: Context?) {
            context?.startActivity(Intent(context, EarnActivity::class.java))
        }
    }

    override fun initToolBar() {
    }

    override fun layoutId(): Int = R.layout.activity_earn

    override fun initView() {
        val fmgs = listOf(
            EarnFragment.getInstance(0),
            EarnFragment.getInstance(1),
            EarnFragment.getInstance(2),
            EarnFragment.getInstance(3)
        )
        val titles = arrayListOf("投资收益", "直推奖", "管理奖", "领导奖")

        val adapter = BaseFragmentAdapter(
            supportFragmentManager,
            fmgs, titles
        )
        viewPager.adapter = adapter
        tabLayout.setViewPager(viewPager)

    }

    override fun initData() {
    }

    override fun initEvent() {
    }

    override fun start() {
    }
}