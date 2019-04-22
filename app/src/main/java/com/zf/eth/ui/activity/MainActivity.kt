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
import com.zf.eth.livedata.UserInfoLiveData
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

    override fun setNotLogin() {
    }

    override fun showLoading() {
        showLoadingDialog()
    }

    override fun dismissLoading() {
        dismissLoadingDialog()
    }

    override fun setUserInfo(bean: UserInfoBean) {
        UserInfoLiveData.value = bean

        initTab()
        tabLayout.currentTab = mIndex
        switchFragment(mIndex)
    }


    override fun initToolBar() {
    }

    private val infoPresenter by lazy { UserInfoPresenter() }


    companion object {

        var mIndex = 0

        fun actionStart(context: Context?, index: Int? = 0) {
            mIndex = index ?: 0
            context?.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    //从我的钱包进来C2C
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        switchFragment(mIndex)
    }

    override fun layoutId(): Int = R.layout.activity_main


    private var mHomeFragment: HomeFragment? = null
    private var mDiscoveryFragment: ChessFragment? = null
    private var mHotFragment: C2CFragment? = null
    private var mMineFragment: MeFragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mIndex = 0
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
            1 -> {
                if (UserInfoLiveData.value?.member?.type == "2") {
                    //锁户
                    mMineFragment?.let { transaction.show(it) }
                            ?: MeFragment.getInstance().let {
                                mMineFragment = it
                                transaction.add(R.id.fl_container, it, "me")
                            }
                } else {
                    //未锁户
                    mDiscoveryFragment?.let { transaction.show(it) }
                            ?: ChessFragment.getInstance().let {
                                mDiscoveryFragment = it
                                transaction.add(R.id.fl_container, it, "discovery")
                            }
                }
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
        val mTabEntities = ArrayList<CustomTabEntity>()

        val mTitles = if (UserInfoLiveData.value?.member?.type == "2")
            listOf(
                    "首页",
                    "我的"
            ) else listOf(
                "首页",
                "棋牌娱乐",
                "C2C",
                "我的"
        )

        val mIconUnSelectIds = if (UserInfoLiveData.value?.member?.type == "2")
            listOf(
                    R.drawable.home_page1,
                    R.drawable.my
            ) else listOf(
                R.drawable.home_page1,
                R.drawable.chess,
                R.drawable.two,
                R.drawable.my
        )

        val mIconSelectIds = if (UserInfoLiveData.value?.member?.type == "2")
            listOf(
                    R.drawable.homepage,
                    R.drawable.my1
            ) else listOf(
                R.drawable.homepage,
                R.drawable.chess1,
                R.drawable.two1,
                R.drawable.my1
        )
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


    override fun start() {
        infoPresenter.requestUserInfo()
    }

    override fun onDestroy() {
        super.onDestroy()
        infoPresenter.detachView()
    }

}
