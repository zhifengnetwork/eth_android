package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.MyOrderContract
import com.zf.eth.mvp.model.MyOrderModel
import com.zf.eth.net.exception.ExceptionHandle

class MyOrderPresenter : BasePresenter<MyOrderContract.View>(), MyOrderContract.Presenter {
    private val model by lazy { MyOrderModel() }
    override fun requestMyOrder(status: String) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.getMyOrder(status)
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when (it.status) {
                        1 -> getMyOrder(it.data)
                        -1 -> {
                        }
                        else -> showError("error", it.status)
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
