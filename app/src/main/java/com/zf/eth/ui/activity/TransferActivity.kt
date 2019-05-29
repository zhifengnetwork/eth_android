package com.zf.eth.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.mvp.bean.ChargeBean
import com.zf.eth.mvp.bean.WalletBean
import com.zf.eth.mvp.contract.TransferContract
import com.zf.eth.mvp.contract.WalletContract
import com.zf.eth.mvp.presenter.TransferPresenter
import com.zf.eth.mvp.presenter.WalletPresenter
import com.zf.eth.showToast
import com.zf.eth.utils.PriceInputFilter
import kotlinx.android.synthetic.main.activity_transfer.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import java.math.BigDecimal
import java.text.DecimalFormat

/**
 * 转账
 */
class TransferActivity : BaseActivity(), TransferContract.View, WalletContract.View {


    //获取自由账户金额
    override fun setWallet(bean: WalletBean) {
        price.text = bean.member.credit2
    }

    //手续费
    override fun setChart(bean: ChargeBean) {
        charge.text = bean.zhuanzhangsxf
    }

    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
    }

    //转账成功
    override fun setTransfer(msg: String) {
        showToast(msg)
        finish()
        WalletActivity.actionStart(this, 3)
    }

    override fun showLoading() {
        showLoadingDialog()
    }

    override fun dismissLoading() {
        dismissLoadingDialog()
    }

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

    private val presenter by lazy { TransferPresenter() }

    private val walletPresenter by lazy { WalletPresenter() }

    override fun initData() {
    }

    override fun initView() {
        walletPresenter.attachView(this)
        presenter.attachView(this)

        //限制输入格式为价格
        input.filters = arrayOf(PriceInputFilter())
    }

    @SuppressLint("SetTextI18n")
    override fun initEvent() {

        confirm.setOnClickListener {
            when {
                input.text.isEmpty() -> showToast("请输入金额")
                peer.text.isEmpty() -> showToast("请输入对方ID")
                input.text.toString().toDouble() <= 0 -> showToast("输入金额不能小于或等于零")
                input.text.toString().toDouble() > price.text.toString().toDouble() -> showToast("输入金额超出可交易金额")
                else -> presenter.requestTransfer(input.text.toString(), peer.text.toString())
            }
        }

        input.addTextChangedListener {

            if (input.text.isNotEmpty() && price.text.isNotEmpty() && charge.text.isNotEmpty()) {
                //输入价格
                val inputPrice = BigDecimal(input.text.toString())
                //手续费
                val chargePrice = BigDecimal(charge.text.toString())
                //用户可交易金额
                val userPrice = BigDecimal(price.text.toString())
                val hundred = BigDecimal("100")
                //扣除手续费
                deductCharge.text = DecimalFormat("0.000000000").format(inputPrice.multiply(chargePrice).divide(hundred))
                //实际到账
                //subtract减号 divide 除法
                trueReceive.text =DecimalFormat("0.000000").format(inputPrice.subtract(inputPrice.multiply(chargePrice).divide(hundred)))
            }

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
        walletPresenter.requestWallet()
        walletPresenter.requestCharge()
    }

    override fun onDestroy() {
        super.onDestroy()
        walletPresenter.detachView()
        presenter.detachView()
    }

}