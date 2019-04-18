package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.LogOutContract
import com.zf.eth.mvp.model.LogOutModel
import com.zf.eth.net.exception.ExceptionHandle

class LogOutPresenter : BasePresenter<LogOutContract.View>(), LogOutContract.Presenter {


    private val model: LogOutModel by lazy { LogOutModel() }

    override fun requestLogOut(money: String) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.requestLogOut(money)
                .subscribe({
                    mRootView?.apply {
                        dismissLoading()
                        when (it.status) {
                            1 -> setLogOut()
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