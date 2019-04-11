package com.zf.eth.ui.fragment

import com.zf.eth.R
import com.zf.eth.base.BaseFragment
import com.zf.eth.ui.activity.GameActivity
import kotlinx.android.synthetic.main.fragment_chess.*

/**
 * 棋牌娱乐
 */
class ChessFragment : BaseFragment() {

    companion object {
        fun getInstance(): ChessFragment {
            return ChessFragment()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_chess

    override fun initView() {
    }

    override fun lazyLoad() {
    }

    override fun initEvent() {
        pour.setOnClickListener {
            GameActivity.actionStart(context)
        }
    }

}