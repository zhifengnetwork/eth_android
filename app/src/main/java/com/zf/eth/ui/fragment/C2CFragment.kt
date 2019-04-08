package com.zf.eth.ui.fragment


import com.google.android.material.tabs.TabLayout
import com.zf.eth.R
import com.zf.eth.base.BaseFragment
import com.zf.eth.ui.adapter.C2CPagerAdapter
import kotlinx.android.synthetic.main.fragment_c2c.*

class C2CFragment : BaseFragment() {
    //在真正的开发中，每个界面的ID可能是不同的，所以这里会接收一个ID
    companion object {
        fun getInstance(): C2CFragment {

            return C2CFragment()
        }
    }



    override fun getLayoutId(): Int = R.layout.fragment_c2c



    private val title = arrayListOf("买入", "卖出")

    val madapter by lazy{C2CPagerAdapter(childFragmentManager,title) }

    var i:Int=0
    override fun initView() {
        //上面导航列表
        viewPager.adapter=madapter
        tabLayout.setupWithViewPager(viewPager)

        //添加自定义布局
        for(i in title.indices){
             val tab=tabLayout.getTabAt(i)
            tab?.setCustomView(madapter.getCustomView(i))
        }


    }

    override fun lazyLoad() {
    }

    override fun initEvent() {
        //选中的item更换背景
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                //                添加选中Tab的逻辑
                if(i==0){
                    tablayoutbg.setBackgroundResource(R.drawable.bg3)
                    i++
                }else if(i==1){
                    tablayoutbg.setBackgroundResource(R.drawable.bg2)
                    i--
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
}

