package com.zf.eth.ui.fragment.game

import com.zf.eth.R
import com.zf.eth.base.BaseFragment

/**
 * 3D下注
 */
class RecordFragment:BaseFragment() {

    companion object {
        fun getInstance(): RecordFragment{
            return RecordFragment()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_record

    override fun initView() {
    }

    override fun lazyLoad() {
    }

    override fun initEvent() {
    }
}