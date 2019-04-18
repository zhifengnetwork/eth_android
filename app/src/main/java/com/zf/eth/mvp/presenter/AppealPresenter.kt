package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.AppealContract
import com.zf.eth.mvp.model.AppealModel
import com.zf.eth.net.exception.ExceptionHandle

class AppealPresenter:BasePresenter<AppealContract.View>(),AppealContract.Presenter{
    private val model by lazy { AppealModel() }
    override fun requestAppeal(page: String) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.getAppeal(page)
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when (it.status) {
                        1 -> getAppeal(it.data)
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