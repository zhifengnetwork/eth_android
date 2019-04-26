package com.zf.eth.ui.fragment


import com.google.android.material.tabs.TabLayout
import com.zf.eth.R
import com.zf.eth.base.BaseFragment
import com.zf.eth.base.BaseFragmentAdapter
import com.zf.eth.ui.activity.C2cDetailActivity
import com.zf.eth.ui.fragment.c2c.ContentFragment
import kotlinx.android.synthetic.main.fragment_c2c.*
import kotlinx.android.synthetic.main.layout_c2c_title.*

class C2CFragment : BaseFragment() {

    //在真正的开发中，每个界面的ID可能是不同的，所以这里会接收一个ID
    companion object {
        fun getInstance(): C2CFragment {

            return C2CFragment()
        }
    }


    override fun getLayoutId(): Int = R.layout.fragment_c2c


    private val title = arrayListOf("买入", "卖出")


    val adapter by lazy {
        BaseFragmentAdapter(
            childFragmentManager, listOf(
                ContentFragment.buildFragment(ContentFragment.BUY),
                ContentFragment.buildFragment(ContentFragment.SELL)
            ), title
        )
    }

    override fun initView() {


        //上面导航列表
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

        //添加自定义布局
        for (i in title.indices) {
            val tab = tabLayout.getTabAt(i)
            tab?.customView = adapter.getCustomView(title, R.layout.layout_c2c_tab_item, i)
        }


    }

    override fun lazyLoad() {
    }

    override fun initEvent() {
        /**选中的item更换背景*/
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                //                添加选中Tab的逻辑
                when (tab.position) {
                    0 -> tablayoutbg.setBackgroundResource(R.drawable.bg2)
                    1 -> tablayoutbg.setBackgroundResource(R.drawable.bg3)
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                //                添加未选中Tab的逻辑
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                //                再次选中tab的逻辑 重复点击

            }
        })
        //界面转跳
        navigation_btn.setOnClickListener {
            C2cDetailActivity.actionStart(context,"")
        }
    }

}

