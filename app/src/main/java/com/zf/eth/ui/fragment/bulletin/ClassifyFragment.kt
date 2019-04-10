package com.zf.eth.ui.fragment.bulletin

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.zf.eth.R
import com.zf.eth.base.BaseFragment
import com.zf.eth.ui.adapter.SystemBulletinIfyAdapter
import kotlinx.android.synthetic.main.layout_bullentin_content.*

class ClassifyFragment:BaseFragment(){

    //在真正的开发中，每个界面的ID可能是不同的，所以这里会接收一个ID
    companion object {
        @JvmStatic
        fun buildFragment(id: String): ClassifyFragment {
            var fragment = ClassifyFragment()
            var bundle = Bundle()
            bundle.putString("id", id)
            fragment.arguments = bundle
            return fragment
        }
    }
    private val adapter by lazy { SystemBulletinIfyAdapter(context) }
    override fun getLayoutId(): Int = R.layout.layout_bullentin_content

    override fun initView() {
         bulletin_rv.layoutManager=LinearLayoutManager(context)
         bulletin_rv.adapter=adapter

    }

    override fun lazyLoad() {

    }

    override fun initEvent() {

    }

}