package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.ConfirmOrderContrect
import com.zf.eth.mvp.model.ConfirmOrderModel
import com.zf.eth.net.exception.ExceptionHandle

class ConfirmOrderPresenter : BasePresenter<ConfirmOrderContrect.View>(), ConfirmOrderContrect.Presenter {
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