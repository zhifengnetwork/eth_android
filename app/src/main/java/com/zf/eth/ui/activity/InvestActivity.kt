package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.ui.fragment.wallet.InvestFragment
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * 投资记录
 */
class InvestActivity : BaseActivity() {

    override fun initToolBar() {
        backLayout.setOnClickListener { finish() }
        titleName.text = "投资记录"
    }


    private var investFragment: InvestFragment? = null

    companion object {

        fun actionStart(context: Context?) {
            val intent = Intent(context, InvestActivity::class.java)

            context?.startActivity(intent)
        }
    }

    override fun layoutId(): Int = R.layout.activity_invest

    override fun initData() {

    }

    override fun initView() {

        val transaction = supportFragmentManager.beginTransaction()
        investFragment?.let {
            transaction.hide(it)
        }
        investFragment?.let { transaction.show(it) }
                ?: InvestFragment.newInstance(InvestFragment.TOUZI).let {
                    investFragment = it
                    transaction.add(R.id.fl_container, it, "invest")
                }
        transaction.commitAllowingStateLoss()
    }

    override fun start() {

    }


    override fun initEvent() {

    }


}