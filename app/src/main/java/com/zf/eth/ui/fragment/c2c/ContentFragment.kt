package com.zf.eth.ui.fragment.c2c

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.zf.eth.R
import com.zf.eth.base.BaseFragment
import com.zf.eth.ui.adapter.ContentAdater
import kotlinx.android.synthetic.main.layout_c2c_content.*

class ContentFragment:BaseFragment(){
    //在真正的开发中，每个界面的ID可能是不同的，所以这里会接收一个ID
    companion object {
        @JvmStatic
        fun buildFragment(id: String): ContentFragment {
            var fragment = ContentFragment()
            var bundle = Bundle()
            bundle.putString("id", id)
            fragment.arguments = bundle
            return fragment
        }
    }

    private val adapter by lazy { ContentAdater(context) }
    override fun getLayoutId(): Int = R.layout.layout_c2c_content

    override fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter=adapter

    }

    override fun lazyLoad() {

    }

    override fun initEvent() {

    }

}