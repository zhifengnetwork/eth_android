package com.zf.eth.ui.fragment

import com.zf.eth.R
import com.zf.eth.base.BaseFragment

class ChessFragment:BaseFragment() {

    companion object {
        fun getInstance(): ChessFragment{
            return ChessFragment()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_chess

    override fun initView() {
    }

    override fun lazyLoad() {
    }

    override fun initEvent() {
    }
}