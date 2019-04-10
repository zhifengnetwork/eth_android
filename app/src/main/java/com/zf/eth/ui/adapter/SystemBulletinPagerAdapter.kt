package com.zf.eth.ui.adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.zf.eth.ui.fragment.bulletin.ClassifyFragment

class SystemBulletinPagerAdapter(fm: FragmentManager, titles:List<String>): FragmentPagerAdapter(fm) {
    private val fragmentList = arrayOfNulls<Fragment>(titles.size)
    val titles=titles
    override fun getItem(position: Int): Fragment {
         return ClassifyFragment.buildFragment(titles[position])
    }

    override fun getCount(): Int {
        return fragmentList.size
    }
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as Fragment
        fragmentList[position] = fragment
        return fragment
    }
    //重写这个方法，将设置每个Tab的标题
    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]

    }
}