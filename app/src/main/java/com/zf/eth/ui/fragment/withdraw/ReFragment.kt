package com.zf.eth.ui.fragment.withdraw

import com.zf.eth.R
import com.zf.eth.base.BaseFragment
import com.zf.eth.mvp.bean.ChargeBean
import com.zf.eth.mvp.bean.WalletBean
import com.zf.eth.mvp.contract.WalletContract
import com.zf.eth.mvp.presenter.WalletPresenter
import com.zf.eth.showToast
import kotlinx.android.synthetic.main.fragment_re.*

/**
 * 复投账户提现
 */
class ReFragment : BaseFragment(), WalletContract.View {

    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
    }

    override fun setWallet(bean: WalletBean) {
        balance.text = bean.member.credit4
    }

    override fun setChart(bean: ChargeBean) {
    }

    override fun showLoading() {
        showLoadingDialog()
    }

    override fun dismissLoading() {
        dismissLoadingDialog()
    }

    companion object {
        fun getInstance(): ReFragment {
            return ReFragment()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_re

    private val walletPresenter by lazy { WalletPresenter() }

    override fun initView() {
        walletPresenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        walletPresenter.detachView()
    }

    override fun lazyLoad() {
        walletPresenter.requestWallet()
    }

    override fun initEvent() {
    }
}