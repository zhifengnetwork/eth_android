package com.zf.eth.ui.fragment.withdraw

import android.annotation.SuppressLint
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.zf.eth.R
import com.zf.eth.base.BaseFragment
import com.zf.eth.mvp.bean.ChargeBean
import com.zf.eth.mvp.bean.WalletBean
import com.zf.eth.mvp.contract.WalletContract
import com.zf.eth.mvp.contract.WithDrawContract
import com.zf.eth.mvp.presenter.WalletPresenter
import com.zf.eth.mvp.presenter.WithDrawPresenter
import com.zf.eth.showToast
import com.zf.eth.ui.activity.WalletActivity
import com.zf.eth.ui.activity.WalletAddressActivity
import com.zf.eth.utils.PriceInputFilter
import kotlinx.android.synthetic.main.fragment_eth.*
import java.math.BigDecimal

/**
 * ETH账户提现
 */
class ETHFragment : BaseFragment(), WithDrawContract.View, WalletContract.View {

    //跳转都钱包地址完善信息
    override fun setPerfectInfo(msg: String) {
        showToast(msg)
        WalletAddressActivity.actionStart(context)
    }

    //手续费
    override fun setChart(bean: ChargeBean) {
        isCharge = bean.withdraw
        //最低提现金额
        minimum_money.text = bean.withdrawmoney
        //手续费
        charge.text = bean.withdrawsxf
    }

    //获取账户余额
    override fun setWallet(bean: WalletBean) {
        price.text = bean.member.credit2
    }

    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
    }

    //提现成功
    override fun setWithDraw(msg: String) {
        showToast(msg)
        activity?.finish()
        WalletActivity.actionStart(context, 2)
    }

    override fun showLoading() {
        showLoadingDialog()
    }

    override fun dismissLoading() {
        dismissLoadingDialog()
    }

    companion object {
        fun getInstance(): ETHFragment {
            return ETHFragment()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_eth

    private val presenter by lazy { WithDrawPresenter() }
    private val walletPresenter by lazy { WalletPresenter() }
    //判断是否能提现
    private var isCharge = 1

    override fun initView() {
        walletPresenter.attachView(this)
        presenter.attachView(this)
        dashLine1.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
    }

    override fun lazyLoad() {
        walletPresenter.requestWallet()
        walletPresenter.requestCharge()
    }

    override fun onDestroy() {
        super.onDestroy()
        walletPresenter.detachView()
        presenter.detachView()
    }

    @SuppressLint("SetTextI18n")
    override fun initEvent() {

        allDraw.setOnClickListener {
            input.setText(price.text)
        }

        confirm.setOnClickListener {
            when {
                input.text.isEmpty() -> showToast("请输入提现金额")
                input.text.toString().toFloat() <= 0 -> showToast("输入金额不能小于或等于零")
                input.text.toString().toDouble() > price.text.toString().toDouble() -> showToast("输入金额超出可提现金额")
                else -> {
                    //0 否 1 能提现
                    if (isCharge == 0) {
                        showToast("系统未开启提现")
                    } else {
                        presenter.requestWithDraw(input.text.toString())
                    }
                }
            }
        }

        input.filters = arrayOf(PriceInputFilter())
        input.addTextChangedListener {

            if (input.text.isNotEmpty() && charge.text.isNotEmpty() && price.text.isNotEmpty()) {
                //计算
                val userPrice = BigDecimal(price.text.toString())
                val inputPrice = BigDecimal(input.text.toString())
                val chargePrice = BigDecimal(charge.text.toString())
                val hundred = BigDecimal("100")
                //扣除手续费
                deductCharge.text = "¥" + inputPrice.multiply(chargePrice).divide(hundred)
                //实际到账  subtract：减号   multiply：乘号 divide：除号
                trueReceive.text = "¥" + inputPrice.subtract(inputPrice.multiply(chargePrice).divide(hundred))
            }

            if (input.text.isEmpty()) {
                //手续费布局
                chargeLayout.visibility = View.GONE
                //实际到账布局
                arrivalLayout.visibility = View.GONE
            } else {
                chargeLayout.visibility = View.VISIBLE
                arrivalLayout.visibility = View.VISIBLE
            }
        }

    }
}