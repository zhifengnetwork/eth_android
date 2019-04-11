package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import androidx.fragment.app.FragmentTransaction
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.ui.fragment.game.PourFragment
import com.zf.eth.ui.fragment.game.RecordFragment
import com.zf.eth.ui.fragment.game.WinFragment
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class GameActivity : BaseActivity() {

    override fun initToolBar() {
        backLayout.setOnClickListener { finish() }
        titleName.text = "3D游戏"
    }

    companion object {
        fun actionStart(context: Context?) {
            context?.startActivity(Intent(context, GameActivity::class.java))
        }
    }

    override fun layoutId(): Int = R.layout.activity_game

    private var mPourFragment: PourFragment? = null
    private var mRecordFragment: RecordFragment? = null
    private var mWinFragment: WinFragment? = null

    private var mIndex = 0

    override fun initView() {

        switchFragment(mIndex)
    }

    private fun switchFragment(index: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (index) {
            0 -> mPourFragment?.let { transaction.show(it) }
                ?: PourFragment.getInstance().let {
                    mPourFragment = it
                    transaction.add(R.id.fl_container, it, "home")
                }
            1 -> mRecordFragment?.let { transaction.show(it) }
                ?: RecordFragment.getInstance().let {
                    mRecordFragment = it
                    transaction.add(R.id.fl_container, it, "discovery")
                }
            2 -> mWinFragment?.let { transaction.show(it) }
                ?: WinFragment.getInstance().let {
                    mWinFragment = it
                    transaction.add(R.id.fl_container, it, "hot")
                }
            else -> {
            }
        }
        mIndex = index
        transaction.commitAllowingStateLoss()
    }

    private fun hideFragments(transaction: FragmentTransaction) {
        mPourFragment?.let {
            transaction.hide(it)
        }
        mRecordFragment?.let {
            transaction.hide(it)
        }
        mWinFragment?.let {
            transaction.hide(it)
        }
    }

    override fun initData() {
    }


    override fun initEvent() {
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                game.id -> switchFragment(0)
                bet.id -> switchFragment(1)
                prize.id -> switchFragment(2)
            }
        }
    }

    override fun start() {
    }
}