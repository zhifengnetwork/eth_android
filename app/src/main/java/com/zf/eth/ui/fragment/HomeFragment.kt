package com.zf.eth.ui.fragment

import com.zf.eth.R
import com.zf.eth.base.BaseFragment
import com.zf.eth.utils.GlideImageLoader
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    companion object {
        fun getInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun initView() {
        bannerTextView.setDatas(arrayListOf("新人注册送金币", "今日开奖是多少"))
        initBanner()
    }

    private val images = arrayListOf(R.mipmap.v1, R.mipmap.v1, R.mipmap.v1, R.mipmap.v1)

    private fun initBanner() {
        banner.setImageLoader(GlideImageLoader())
        banner.setImages(images)
        banner.start()
    }

    override fun onResume() {
        super.onResume()
        bannerTextView.startViewAnimator()
    }

    override fun onStop() {
        super.onStop()
        bannerTextView.stopViewAnimator()
    }

    override fun lazyLoad() {
    }

    override fun initEvent() {
    }
}