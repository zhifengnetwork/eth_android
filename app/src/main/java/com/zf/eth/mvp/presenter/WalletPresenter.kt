package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.WalletContract
import com.zf.eth.mvp.model.WalletModel
import com.zf.eth.net.exception.ExceptionHandle

class WalletPresenter : BasePresenter<WalletContract.View>(), WalletContract.Presenter {


    private val model: WalletModel by lazy { WalletModel() }

    //请求手续费
    override fun requestCharge() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.requestCharge()
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when (it.status) {
                        1 -> setChart(it.data)
                        -1 -> {
                        }
                        else -> showError(it.msg, it.status)
                    }
                }
            }, {
                mRootView?.apply {
                    dismissLoading()
                    showError(ExceptionHandle.handleException(it), ExceptionHandle.errorCode)
                }
            })
        addSubscription(disposable)
    }


    override fun requestWallet() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.requestWallet()
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when (it.status) {
                        1 -> setWallet(it.data)
                        -1 -> {
                        }
                        else -> showError(it.msg, it.status)
                    }
                }
            }, {
                mRootView?.apply {
                    dismissLoading()
                    showError(ExceptionHandle.handleException(it), ExceptionHandle.errorCode)
                }
            })
        addSubscription(disposable)
    }
}