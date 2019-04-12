package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.InviteContract
import com.zf.eth.mvp.model.InviteModel
import com.zf.eth.net.exception.ExceptionHandle

class InvitePresenter:BasePresenter<InviteContract.View>(),InviteContract.Presenter{
    private val model by lazy { InviteModel() }
    override fun requestInvite() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.getInvite()
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when(it.status){
                        1-> getInvite(it.data)
                        else -> showError("error", it.status)
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