package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.HangonsaleContract
import com.zf.eth.mvp.model.HangonsaleModel
import com.zf.eth.net.exception.ExceptionHandle

class HangonsalePresenter : BasePresenter<HangonsaleContract.View>(), HangonsaleContract.Presenter {
    private val model by lazy { HangonsaleModel() }
    override fun requesHangonsale(type: String, price: String, money: String, sxf0: String, trx: String, trx2: String) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.setHangonsale(type, price, money, sxf0, trx, trx2)
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when (it.status) {
                        1 -> setHangonsaleSuccess(it.msg)
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

    override fun requestEther() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.getEther()
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when (it.status) {
                        1 -> getEther(it.data.list)
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