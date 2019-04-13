package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.base.BaseFragmentAdapter
import com.zf.eth.ui.fragment.earn.EarnFragment
import com.zf.eth.utils.LogUtils
import kotlinx.android.synthetic.main.activity_earn.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * 总收益 和 今日收益
 */
class EarnActivity : BaseActivity() {

    companion object {
        const val TODAY = "member.androidapi.today_record"
        const val TOTAL = "member.androidapi.income_record"
        fun actionStart(context: Context?, type: String) {
            val intent = Intent(context, EarnActivity::class.java)
            intent.putExtra("type", type)
            context?.startActivity(intent)
        }
    }

    private var mType = ""

    override fun initData() {
        mType = intent.getStringExtra("type")
        LogUtils.e(">>>>initD:"+mType)
    }

    override fun initToolBar() {
        backLayout.setOnClickListener { finish() }
        LogUtils.e(">>>>>:"+mType)
        titleName.text = if (mType == TODAY) "今日收益明细" else "收益明细"
    }

    override fun layoutId(): Int = R.layout.activity_earn

    override fun initView() {
        val fmgs = listOf(
                EarnFragment.getInstance(0, mType),
                EarnFragment.getInstance(1, mType),
                EarnFragment.getInstance(2, mType),
                EarnFragment.getInstance(3, mType)
        )
        val titles = arrayListOf("投资收益", "直推奖", "管理奖", "领导奖")

        val adapter = BaseFragmentAdapter(supportFragmentManager, fmgs, titles)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 4
        tabLayout.setViewPager(viewPager)

    }

    override fun initEvent() {
    }

    override fun start() {
    }
}