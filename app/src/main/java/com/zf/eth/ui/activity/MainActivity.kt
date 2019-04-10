package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.FragmentTransaction
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.mvp.bean.TabEntity
import com.zf.eth.mvp.bean.UserInfoBean
import com.zf.eth.mvp.contract.UserInfoContract
import com.zf.eth.mvp.presenter.UserInfoPresenter
import com.zf.eth.showToast
import com.zf.eth.ui.fragment.C2CFragment
import com.zf.eth.ui.fragment.ChessFragment
import com.zf.eth.ui.fragment.HomeFragment
import com.zf.eth.ui.fragment.MeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), UserInfoContract.View {

    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
    }

    override fun setUserInfo(bean: UserInfoBean) {
        showToast(bean.result.message)
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    override fun initToolBar() {
    }

    companion object {
        fun actionStart(context: Context?) {
            context?.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    override fun layoutId(): Int = R.layout.activity_main

    private val mTitles = listOf("首页", "棋牌娱乐", "C2C", "我的")

    private val mIconSelectIds = listOf(
        R.drawable.homepage,
        R.drawable.chess1,
        R.drawable.two1,
        R.drawable.my1
    )

    private val mIconUnSelectIds = listOf(
        R.drawable.home_page1,
        R.drawable.chess,
        R.drawable.two,
        R.drawable.my
    )

    private val mTabEntities = ArrayList<CustomTabEntity>()

    private var mHomeFragment: HomeFragment? = null
    private var mDiscoveryFragment: ChessFragment? = null
    private var mHotFragment: C2CFragment? = null
    private var mMineFragment: MeFragment? = null

    private var mIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            mIndex = savedInstanceState.getInt("currTabIndex")
        }
        super.onCreate(savedInstanceState)
        initTab()
        tabLayout.currentTab = mIndex
        switchFragment(mIndex)
    }

    private fun switchFragment(index: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (index) {
            0 -> mHomeFragment?.let { transaction.show(it) }
                ?: HomeFragment.getInstance().let {
                    mHomeFragment = it
                    transaction.add(R.id.fl_container, it, "home")
                }
            1 -> mDiscoveryFragment?.let { transaction.show(it) }
                ?: ChessFragment.getInstance().let {
                    mDiscoveryFragment = it
                    transaction.add(R.id.fl_container, it, "discovery")
                }
            2 -> mHotFragment?.let { transaction.show(it) }
                ?: C2CFragment.getInstance().let {
                    mHotFragment = it
                    transaction.add(R.id.fl_container, it, "hot")
                }
            3 -> mMineFragment?.let { transaction.show(it) }
                ?: MeFragment.getInstance().let {
                    mMineFragment = it
                    transaction.add(R.id.fl_container, it, "mine")
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
        mMineFragment?.let {
            transaction.hide(it)
        }
    }

    private fun initTab() {
        (0 until mTitles.size).mapTo(mTabEntities) {
            TabEntity(mTitles[it], mIconSelectIds[it], mIconUnSelectIds[it])
        }
        tabLayout.setTabData(mTabEntities)
        tabLayout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabReselect(position: Int) {}
            override fun onTabSelect(position: Int) {
                switchFragment(position)
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //记录fragment的位置,防止崩溃 activity被系统回收时，fragment错乱
        if (tabLayout != null) {
            outState.putInt("currTabIndex", mIndex)
        }
    }


    private var mExitTime: Long = 0
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
                finish()
            } else {
                mExitTime = System.currentTimeMillis()
                showToast("再按一次退出程序")
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun initData() {
    }

    override fun initView() {
        infoPresenter.attachView(this)
    }

    override fun initEvent() {
    }

    private val infoPresenter by lazy { UserInfoPresenter() }

    override fun start() {
//        infoPresenter.requestUserInfo()
    }

    override fun onDestroy() {
        super.onDestroy()
        infoPresenter.detachView()
    }

}
