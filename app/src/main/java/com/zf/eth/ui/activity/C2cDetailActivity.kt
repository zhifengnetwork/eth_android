package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.material.tabs.TabLayout
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.base.BaseFragmentAdapter
import com.zf.eth.ui.fragment.c2c.AdvertFragment
import com.zf.eth.ui.fragment.c2c.AppealFragment
import com.zf.eth.ui.fragment.c2c.OrderFragment
import kotlinx.android.synthetic.main.activity_c2c_detail.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class C2cDetailActivity : BaseActivity() {
    companion object {
        fun actionStart(context: Context?, type: String) {
            val intent = Intent(context, C2cDetailActivity::class.java)
            intent.putExtra("mType", type)
            context?.startActivity(intent)
        }
    }

    override fun initToolBar() {
        titleName.text = "ETH/CNY"
        titleName.textSize = 22f
        titleName.paint.isFakeBoldText = true
        titleBackground.setBackgroundResource(R.drawable.bg1)

        backLayout.setOnClickListener {
            finish()
        }
    }

    override fun layoutId(): Int = R.layout.activity_c2c_detail


    override fun initData() {

    }

    override fun initView() {
        val type = intent.getStringExtra( "mType")

        val mTitles = arrayListOf("我的订单", "发布广告", "我的申诉")
        val mFragment =
            arrayListOf(OrderFragment.getInstance(type), AdvertFragment.getInstance(), AppealFragment.getInstance())
        val adapter = BaseFragmentAdapter(supportFragmentManager, mFragment, mTitles)
        detail_vp.adapter = adapter
        detail_tab.setupWithViewPager(detail_vp)
        //添加自定义布局
        for (i in mTitles.indices) {
            val tab = detail_tab.getTabAt(i)
            tab?.customView = adapter.getCustomView(mTitles, R.layout.layout_c2c_detail_tabtitle, i)
        }
    }

    override fun initEvent() {
        /**选中的item更换背景*/
        detail_tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                //   添加选中Tab的逻辑

                when (tab.position) {
                    0 -> {
                        detail_ly.setBackgroundResource(R.drawable.detail_bg1)

                    }
                    1 -> {
                        detail_ly.setBackgroundResource(R.drawable.detail_bg2)

                    }
                    2 -> {
                        detail_ly.setBackgroundResource(R.drawable.detail_bg3)

                    }
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                //                添加未选中Tab的逻辑

            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                //                再次选中tab的逻辑 重复点击

            }
        })
    }

    override fun start() {

    }

}