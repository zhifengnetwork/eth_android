package com.zf.eth.ui.fragment.c2c

import androidx.recyclerview.widget.LinearLayoutManager
import com.zf.eth.R
import com.zf.eth.base.BaseFragment
import com.zf.eth.ui.adapter.OrderContentAdapter
import kotlinx.android.synthetic.main.fragment_c2c_order_content.*

class OrderContentFragment:BaseFragment(){

    companion object {
        fun getInstance():OrderContentFragment {

            return OrderContentFragment()
        }
    }
    override fun getLayoutId(): Int = R.layout.fragment_c2c_order_content

    private val mAdapter by lazy { OrderContentAdapter(context) }

    override fun initView() {

        order_rv.layoutManager=LinearLayoutManager(context)
        order_rv.adapter=mAdapter
    }

    override fun lazyLoad() {

    }

    override fun initEvent() {

    }

}