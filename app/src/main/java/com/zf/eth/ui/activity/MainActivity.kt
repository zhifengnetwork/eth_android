package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.FragmentTransaction
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.fm.openinstall.OpenInstall
import com.fm.openinstall.listener.AppWakeUpAdapter
import com.fm.openinstall.model.AppData
import com.zf.eth.R
import com.zf.eth.api.UriConstant
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
import com.zf.eth.utils.bus.RxBus
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), UserInfoContract.View {

    override fun showError(msg: String, errorCode: Int) {
//        showToast(msg)
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

        OpenInstall.getWakeUp(intent, wakeUpAdapter)
    }

    override fun layoutId(): Int = R.layout.activity_main

    private var mLabel = 4
    private var mHomeFragment: HomeFragment? = null
    private var mDiscoveryFragment: ChessFragment? = null
    private var mHotFragment: C2CFragment? = null
    private var mMineFragment: MeFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTab()
        switchFragment(mIndex)

        initOpenInstall()

    }

    private var wakeUpAdapter: AppWakeUpAdapter? = null

    private fun initOpenInstall() {

        wakeUpAdapter = object : AppWakeUpAdapter() {
            override fun onWakeUp(appData: AppData) {

            }
        }

        OpenInstall.getWakeUp(intent, wakeUpAdapter)
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
                if (UserInfoLiveData.value?.member?.type == "2" || UserInfoLiveData.value?.member?.suoding == "1") {
                    //锁户
                    mMineFragment?.let { transaction.show(it) }
                        ?: MeFragment.getInstance().let {
                            mMineFragment = it
                            transaction.add(R.id.fl_container, it, "me")
                        }
                    start()
                    //每次到我的界面请求用户等级提升接口
                    RxBus.getDefault().post(UriConstant.USER_LEVEL, UriConstant.USER_LEVEL)
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
            3 -> {
                mMineFragment?.let { transaction.show(it) }
                    ?: MeFragment.getInstance().let {
                        mMineFragment = it
                        transaction.add(R.id.fl_container, it, "mine")
                    }
                start()
                //每次到我的界面请求用户等级提升接口
                RxBus.getDefault().post(UriConstant.USER_LEVEL, UriConstant.USER_LEVEL)
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
        val mTitles = if (UserInfoLiveData.value?.member?.type == "2" || UserInfoLiveData.value?.member?.suoding == "1")
            listOf(
                "首页",
                "我的"
            ) else listOf(
            "首页",
            "棋牌娱乐",
            "C2C",
            "我的"
        )

        val mIconUnSelectIds =
            if (UserInfoLiveData.value?.member?.type == "2" || UserInfoLiveData.value?.member?.suoding == "1")
                listOf(
                    R.drawable.home_page1,
                    R.drawable.my
                ) else listOf(
                R.drawable.home_page1,
                R.drawable.chess,
                R.drawable.two,
                R.drawable.my
            )

        val mIconSelectIds =
            if (UserInfoLiveData.value?.member?.type == "2" || UserInfoLiveData.value?.member?.suoding == "1")
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
        if (mIndex > mTitles.size) {
            mIndex = mTitles.size - 1
        }

        if (mTitles.size == 2) {
            mLabel = mTitles.size
        }

        if (mLabel != mTitles.size) {
            mIndex = 3
        }
        tabLayout.currentTab = mIndex
//        switchFragment(mIndex)

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
        RxBus.getDefault().subscribe<String>(this, UriConstant.FRESH_USER_INFO) {
            infoPresenter.requestUserInfo()

        }
        RxBus.getDefault().subscribe<String>(this, UriConstant.USER_SWITCH) {
            infoPresenter.requestUserInfo()

        }
    }

    override fun start() {
        infoPresenter.requestUserInfo()
    }

    override fun onDestroy() {
        super.onDestroy()
        mIndex = 0
        tabLayout.currentTab = mIndex
        infoPresenter.detachView()
        wakeUpAdapter = null
    }

}
