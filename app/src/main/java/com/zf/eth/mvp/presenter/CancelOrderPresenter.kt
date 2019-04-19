package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.CancelOrderContract
import com.zf.eth.mvp.model.CancelOrderModel
import com.zf.eth.net.exception.ExceptionHandle

class CancelOrderPresenter:BasePresenter<CancelOrderContract.View>(),CancelOrderContract.Presenter{
    override fun requestOrderDetail(id: String) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.requestOrderDetail(id)
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when(it.status){
                        1 -> getOrderDetail(it.data)
                        else -> showError(it.msg, it.status)
                    }
                }
            },{
                mRootView?.apply {
                    dismissLoading()
                    showError(ExceptionHandle.handleException(it), ExceptionHandle.errorCode)
                }
            })
        addSubscription(disposable)
    }

    private val model by lazy { CancelOrderModel() }
    override fun requestCancelOrder(id: String) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.requestCancelOrder(id)
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when(it.status){
                        1 -> CancelOrderSuccess(it.msg)
                        else -> showError(it.msg, it.status)
                    }
                }
            },{
                mRootView?.apply {
                    dismissLoading()
                    showError(ExceptionHandle.handleException(it), ExceptionHandle.errorCode)
                }
            })
        addSubscription(disposable)
    }

}