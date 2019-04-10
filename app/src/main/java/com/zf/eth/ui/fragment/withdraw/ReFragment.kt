package com.zf.eth.ui.fragment.withdraw

import com.zf.eth.R
import com.zf.eth.base.BaseFragment

/**
 * 复投账户提现
 */
class ReFragment : BaseFragment() {

    companion object {
        fun getInstance(): ReFragment {
            return ReFragment()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_re

    override fun initView() {
    }

    override fun lazyLoad() {
    }

    override fun initEvent() {
    }
}