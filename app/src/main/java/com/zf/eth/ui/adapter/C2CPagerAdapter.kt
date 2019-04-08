package com.zf.eth.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.zf.eth.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.zf.eth.MyApplication.Companion.context
import com.zf.eth.ui.fragment.c2c.ContentFragment


class C2CPagerAdapter(fm:FragmentManager, titles:List<String>): FragmentPagerAdapter(fm) {
    private val fragmentList = arrayOfNulls<Fragment>(titles.size)
    val titles=titles
    override fun getItem(position: Int): Fragment {
         return ContentFragment.buildFragment(titles[position])
    }

    override fun getCount(): Int {
        return fragmentList.size
    }
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as Fragment
        fragmentList[position] = fragment
        return fragment
    }

    //注意！！！这里就是我们自定义的布局tab_item
    fun getCustomView(position:Int): View? {
        val view=LayoutInflater.from(context).inflate(R.layout.layout_c2c_tab_item, null)
        val tv =view.findViewById<TextView>(R.id.tabname)
        tv.text=titles[position]
        return view
    }
    //重写这个方法，将设置每个Tab的标题
//    override fun getPageTitle(position: Int): CharSequence? {
//        return titles[position];
//
//    }
}