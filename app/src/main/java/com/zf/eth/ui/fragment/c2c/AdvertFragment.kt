package com.zf.eth.ui.fragment.c2c

import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.flyco.tablayout.listener.OnTabSelectListener
import com.zf.eth.R
import com.zf.eth.base.BaseFragment
import com.zf.eth.base.BaseFragmentAdapter
import kotlinx.android.synthetic.main.fragment_c2c_advert.*

class AdvertFragment:BaseFragment(){

    companion object {
        fun getInstance():AdvertFragment {

            return AdvertFragment()
        }
    }
    override fun getLayoutId(): Int = R.layout.fragment_c2c_advert

    private val mTitles= arrayOf("买入ETH","卖出ETH")
    private val mFragment = arrayListOf<Fragment>(AdvertContentFragment.getInstance(AdvertContentFragment.BUY),AdvertContentFragment.getInstance(AdvertContentFragment.SELL))
     private val mAdapter by lazy { BaseFragmentAdapter(childFragmentManager,mFragment) }
    override fun initView() {

        advert_vp.adapter=mAdapter
        advert_tab.setTabData(mTitles)
    }

    override fun lazyLoad() {

    }

    override fun initEvent() {
        //TabLayout点击事件
        advert_tab.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                advert_vp.currentItem=position
            }

            override fun onTabReselect(position: Int) {

            }

        } )
        //ViewPager滑动监听
        advert_vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                advert_tab.currentTab=position
            }

        })


    }

}