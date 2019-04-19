package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.AppealListContract
import com.zf.eth.mvp.model.AppealListModel
import com.zf.eth.net.exception.ExceptionHandle

class AppealListPresenter:BasePresenter<AppealListContract.View>(),AppealListContract.Presenter{
    private val model by lazy { AppealListModel() }
    override fun requestAppealList(id: String) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.getAppealList(id )
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when (it.status) {
                        1 -> getAppealList(it.data)
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