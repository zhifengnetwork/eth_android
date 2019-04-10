package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import android.view.View
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import kotlinx.android.synthetic.main.activity_wallet.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import kotlinx.android.synthetic.main.layout_wallet_tab.*

/**
 * 钱包余额
 */
class WalletActivity : BaseActivity() {

    companion object {
        fun actionStart(context: Context?) {
            context?.startActivity(Intent(context, WalletActivity::class.java))
        }
    }

    override fun initToolBar() {
        backLayout.setOnClickListener { finish() }
        titleName.text = "钱包余额"
    }

    override fun layoutId(): Int = R.layout.activity_wallet

    override fun initData() {
    }

    override fun initView() {
        dashLine1.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        dashLine2.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
    }

    override fun initEvent() {

        //互转
        transfer.setOnClickListener {
            TransferActivity.actionStart(this)
        }

        //提现
        withdraw.setOnClickListener {
            WithdrawActivity.actionStart(this)
        }

        //复投账户一键复投
        reVoting.setOnClickListener {
            VotingActivity.actionStart(this, VotingActivity.RE_DELIVER)
        }

        //自由钱包一键复投
        freeVoting.setOnClickListener {
            VotingActivity.actionStart(this, VotingActivity.FREE_WALLET)
        }

        //提币记录
        mentionRecord.setOnClickListener {
            InvestActivity.actionStart(this, InvestActivity.TIBI)
        }

        //转币记录
        turnRecord.setOnClickListener {
            InvestActivity.actionStart(this, InvestActivity.ZHUANBI)
        }

        //C2C记录
        c2cRecord.setOnClickListener {
            C2CRecordActivity.actionStart(this)
        }

        //复投账户
        reAccountLayout.setOnClickListener {
            reAccountLayout.isSelected = !reAccountLayout.isSelected
            if (reAccountLayout.isSelected) {
                reDetailLayout.visibility = View.VISIBLE
            } else {
                reDetailLayout.visibility = View.GONE
            }
        }

        //自由钱包
        freeAccountLayout.setOnClickListener {
            freeAccountLayout.isSelected = !freeAccountLayout.isSelected
            if (freeAccountLayout.isSelected) {
                freeDetailLayout.visibility = View.VISIBLE
            } else {
                freeDetailLayout.visibility = View.GONE
            }
        }

    }

    override fun start() {
    }
}