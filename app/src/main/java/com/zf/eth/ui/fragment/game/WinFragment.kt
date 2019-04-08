package com.zf.eth.ui.fragment.game

import com.zf.eth.R
import com.zf.eth.base.BaseFragment

/**
 * 中奖记录
 */
class WinFragment : BaseFragment() {

    companion object {
        fun getInstance(): WinFragment {
            return WinFragment()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_win

    override fun initView() {
    }

    override fun lazyLoad() {
    }

    override fun initEvent() {
    }
}