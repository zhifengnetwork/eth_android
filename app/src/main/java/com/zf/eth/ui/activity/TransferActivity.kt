package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import kotlinx.android.synthetic.main.activity_transfer.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * 转账
 */
class TransferActivity : BaseActivity() {

    companion object {
        fun actionStart(context: Context?) {
            context?.startActivity(Intent(context, TransferActivity::class.java))
        }
    }

    override fun initToolBar() {
        backLayout.setOnClickListener { finish() }
        titleName.text = "转账"
    }

    override fun layoutId(): Int = R.layout.activity_transfer

    override fun initData() {
    }

    override fun initView() {
    }

    override fun initEvent() {
        input.addTextChangedListener {
            if (input.text.isNotEmpty()) {
                chargeLayout.visibility = View.VISIBLE
                arrivalLayout.visibility = View.VISIBLE
            } else {
                chargeLayout.visibility = View.GONE
                arrivalLayout.visibility = View.GONE
            }
        }
    }

    override fun start() {
    }
}