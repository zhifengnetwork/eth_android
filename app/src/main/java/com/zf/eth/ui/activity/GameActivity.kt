package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import androidx.fragment.app.FragmentTransaction
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.mvp.bean.TabEntity
import com.zf.eth.ui.fragment.game.PourFragment
import com.zf.eth.ui.fragment.game.RecordFragment
import com.zf.eth.ui.fragment.game.WinFragment
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : BaseActivity() {

    override fun initToolBar() {

    }

    companion object {
        fun actionStart(context: Context?) {
            context?.startActivity(Intent(context, GameActivity::class.java))
        }
    }

    override fun layoutId(): Int = R.layout.activity_game

    private val mTitles = listOf("3D下注", "押注记录", "中奖记录")
    private var mHomeFragment: PourFragment? = null
    private var mDiscoveryFragment: RecordFragment? = null
    private var mHotFragment: WinFragment? = null

    private var mIndex = 0

    override fun initView() {
        initTab()
        tabLayout.currentTab = mIndex
        switchFragment(mIndex)
    }

    private val mTabEntities = ArrayList<CustomTabEntity>()

    private fun initTab() {
        (0 until mTitles.size).mapTo(mTabEntities) {
            TabEntity(mTitles[it], 0, 0)
        }
        tabLayout.setTabData(mTabEntities)
        tabLayout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabReselect(position: Int) {}
            override fun onTabSelect(position: Int) {
                switchFragment(position)
            }
        })
    }

    private fun switchFragment(index: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (index) {
            0 -> mHomeFragment?.let { transaction.show(it) }
                ?: PourFragment.getInstance().let {
                    mHomeFragment = it
                    transaction.add(R.id.fl_container, it, "home")
                }
            1 -> mDiscoveryFragment?.let { transaction.show(it) }
                ?: RecordFragment.getInstance().let {
                    mDiscoveryFragment = it
                    transaction.add(R.id.fl_container, it, "discovery")
                }
            2 -> mHotFragment?.let { transaction.show(it) }
                ?: WinFragment.getInstance().let {
                    mHotFragment = it
                    transaction.add(R.id.fl_container, it, "hot")
                }
            else -> {
            }
        }
        mIndex = index
        tabLayout.currentTab = mIndex
        transaction.commitAllowingStateLoss()
    }

    private fun hideFragments(transaction: FragmentTransaction) {
        mHomeFragment?.let {
            transaction.hide(it)
        }
        mDiscoveryFragment?.let {
            transaction.hide(it)
        }
        mHotFragment?.let {
            transaction.hide(it)
        }

    }

    override fun initData() {
    }


    override fun initEvent() {
    }

    override fun start() {
    }
}