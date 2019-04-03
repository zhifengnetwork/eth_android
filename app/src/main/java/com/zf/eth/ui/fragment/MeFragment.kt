package com.zf.eth.ui.fragment

import com.zf.eth.R
import com.zf.eth.base.BaseFragment

class MeFragment : BaseFragment() {

    companion object {
        fun getInstance(): MeFragment {
            return MeFragment()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_me

    override fun initView() {
    }

    override fun lazyLoad() {
    }

    override fun initEvent() {
    }
}