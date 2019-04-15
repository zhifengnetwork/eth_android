package com.zf.eth.ui.fragment.c2c

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.flyco.tablayout.listener.OnTabSelectListener
import com.zf.eth.R
import com.zf.eth.base.BaseFragment
import com.zf.eth.base.BaseFragmentAdapter
import com.zf.eth.ui.adapter.C2cPagerAdapter
import kotlinx.android.synthetic.main.fragment_c2c_order.*

class OrderFragment:BaseFragment(){

    companion object {
        fun getInstance():OrderFragment {

            return OrderFragment()
        }
    }
    override fun getLayoutId(): Int = R.layout.fragment_c2c_order



    override fun initView() {
         val mTitles= arrayOf("未交易","交易中","交易完成","交易失败")
         val mFragment= arrayListOf<Fragment>(OrderContentFragment.getInstance(),OrderContentFragment.getInstance(),OrderContentFragment.getInstance(),OrderContentFragment.getInstance())
//    private val mAdapter by lazy {  C2cPagerAdapter(childFragmentManager,mTitles) }
         val mAdapter=  BaseFragmentAdapter(childFragmentManager,mFragment)

        order_vp.adapter=mAdapter
        order_tab.setTabData(mTitles)
    }

    override fun lazyLoad() {

    }

    override fun initEvent() {
       //TabLayout点击事件
        order_tab.setOnTabSelectListener(object : OnTabSelectListener{
            override fun onTabSelect(position: Int) {
                order_vp.currentItem=position
            }

            override fun onTabReselect(position: Int) {

            }

        } )
        //ViewPager滑动监听
        order_vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                order_tab.currentTab=position
            }

            override fun onPageSelected(position: Int) {

            }

        })



    }

}