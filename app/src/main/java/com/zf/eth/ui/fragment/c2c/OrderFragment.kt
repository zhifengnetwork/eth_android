package com.zf.eth.ui.fragment.c2c

import com.zf.eth.R
import com.zf.eth.base.BaseFragment

class OrderFragment:BaseFragment(){
    //在真正的开发中，每个界面的ID可能是不同的，所以这里会接收一个ID
    companion object {
        fun getInstance():OrderFragment {

            return OrderFragment()
        }
    }
    override fun getLayoutId(): Int = R.layout.fragment_c2c_order

    override fun initView() {

    }

    override fun lazyLoad() {

    }

    override fun initEvent() {

    }

}