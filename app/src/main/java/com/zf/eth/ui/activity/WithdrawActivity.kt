package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import androidx.fragment.app.FragmentTransaction
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.ui.fragment.withdraw.ETHFragment
import com.zf.eth.ui.fragment.withdraw.ReFragment
import kotlinx.android.synthetic.main.activity_withdraw.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * 提现
 */
class WithdrawActivity : BaseActivity() {

    companion object {
        fun actionStart(context: Context?) {
            context?.startActivity(Intent(context, WithdrawActivity::class.java))
        }
    }

    override fun initToolBar() {
        titleName.text = "提现"
        backLayout.setOnClickListener { finish() }
    }

    override fun layoutId(): Int = R.layout.activity_withdraw

    override fun initData() {
    }

    override fun initView() {
        switchFragment(0)
    }

    private var ethFragment: ETHFragment? = null
    private var reFragment: ReFragment? = null

    private fun switchFragment(index: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (index) {
            0 -> ethFragment?.let { transaction.show(it) }
                ?: ETHFragment.getInstance().let {
                    ethFragment = it
                    transaction.add(R.id.fl_container, it, "")
                }
            1 -> reFragment?.let { transaction.show(it) }
                ?: ReFragment.getInstance().let {
                    reFragment = it
                    transaction.add(R.id.fl_container, it, "")
                }
            else -> {
            }
        }
        transaction.commitAllowingStateLoss()
    }

    private fun hideFragments(transaction: FragmentTransaction) {
        ethFragment?.let {
            transaction.hide(it)
        }
        reFragment?.let {
            transaction.hide(it)
        }
    }

    override fun initEvent() {
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                ethRb.id -> {
                    switchFragment(0)
                }
                reRb.id -> {
                    switchFragment(1)
                }
            }
        }
    }

    override fun start() {
    }
}