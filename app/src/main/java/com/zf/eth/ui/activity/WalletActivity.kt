package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.base.BaseFragmentAdapter
import com.zf.eth.livedata.UserInfoLiveData
import com.zf.eth.ui.fragment.wallet.InvestFragment
import com.zf.eth.ui.fragment.wallet.WalletFragment
import kotlinx.android.synthetic.main.activity_wallet.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * 钱包余额
 */
class WalletActivity : BaseActivity() {

    companion object {
        fun actionStart(context: Context?, type: Int) {
            val intent = Intent(context, WalletActivity::class.java)
            intent.putExtra("type", type)
            context?.startActivity(intent)
        }
    }

    /**
     * 提币成功和转币成功跳转到对应页面
     */
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val mType = intent?.getIntExtra("type", 0) ?: 0
        tabLayout.currentTab = mType
        viewPager.currentItem = mType
    }

    override fun initToolBar() {
        backLayout.setOnClickListener { finish() }
        titleName.text = "钱包余额"
    }

    override fun layoutId(): Int = R.layout.activity_wallet

    private var fmgs = ArrayList<Fragment>()

    private var titles = ArrayList<String>()

    override fun initData() {

    }

    override fun initView() {

//       val fmgs = arrayListOf(
//            WalletFragment.newInstance(),
//            InvestFragment.newInstance(InvestFragment.TOTAL),
//            InvestFragment.newInstance(InvestFragment.TIBI),
//            InvestFragment.newInstance(InvestFragment.ZHUANBI),
//            InvestFragment.newInstance(InvestFragment.C2C)
//        )
//        val titles = arrayListOf("钱包", "总记录", "提币记录", "转币记录", "C2C记录")

        when {
            UserInfoLiveData.value?.member?.type == "2" -> {
                titles = arrayListOf("钱包", "提币记录")
                fmgs = arrayListOf(WalletFragment.newInstance(), InvestFragment.newInstance(InvestFragment.TIBI))
            }
            UserInfoLiveData.value?.member?.suoding == "1" -> {
                titles = arrayListOf("钱包", "总记录")
                fmgs = arrayListOf(WalletFragment.newInstance(), InvestFragment.newInstance(InvestFragment.TOTAL))
            }
            else -> {
                titles = arrayListOf("钱包", "总记录", "提币记录", "转币记录", "C2C记录")
                fmgs = arrayListOf(
                    WalletFragment.newInstance(),
                    InvestFragment.newInstance(InvestFragment.TOTAL),
                    InvestFragment.newInstance(InvestFragment.TIBI),
                    InvestFragment.newInstance(InvestFragment.ZHUANBI),
                    InvestFragment.newInstance(InvestFragment.C2C)
                )
            }
        }

        val adapter = BaseFragmentAdapter(supportFragmentManager, fmgs, titles)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = titles.size
        tabLayout.setViewPager(viewPager)

    }

    override fun initEvent() {

    }

    override fun start() {

    }
}