package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.BuyContract
import com.zf.eth.mvp.model.BuyModel
import com.zf.eth.net.exception.ExceptionHandle

class BuyPresenter : BasePresenter<BuyContract.View>(), BuyContract.Presenter {


    private val model: BuyModel by lazy { BuyModel() }

    override fun requestConfirmPay(money: String, url: String) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.requestConfirmPay(money, url)
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when (it.status) {
                        1 -> setConfirmPay()
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

    //上传支付凭证
    override fun requestPayImg(file: String) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.requestPayImg(file)
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when (it.status) {
                        1 -> setPayImg(it.data.img)
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

    //获取信息
    override fun requestBuyInfo() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.requestBuyInfo()
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when (it.status) {
                        1 -> setBuyInfo(it.data)
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