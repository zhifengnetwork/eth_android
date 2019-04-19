package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.CancelOrderContract
import com.zf.eth.mvp.model.CancelOrderModel
import com.zf.eth.net.exception.ExceptionHandle

class CancelOrderPresenter:BasePresenter<CancelOrderContract.View>(),CancelOrderContract.Presenter{
    private val model by lazy { CancelOrderModel() }
    override fun requesCancelOrder(id: String) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.requesCancelOrder(id)
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