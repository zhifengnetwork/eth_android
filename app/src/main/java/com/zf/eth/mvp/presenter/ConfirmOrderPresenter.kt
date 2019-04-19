package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.ConfirmOrderContrect
import com.zf.eth.mvp.model.ConfirmOrderModel
import com.zf.eth.net.exception.ExceptionHandle

class ConfirmOrderPresenter : BasePresenter<ConfirmOrderContrect.View>(), ConfirmOrderContrect.Presenter {
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

    private val model by lazy { ConfirmOrderModel() }
    override fun requestConfirmOrder(id: String, file: String) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.setConfirmOrder(id, file)
                .subscribe({
                    mRootView?.apply {
                        dismissLoading()
                        when (it.status) {
                            1 -> setConfirmOrderSuccess(it.msg)
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