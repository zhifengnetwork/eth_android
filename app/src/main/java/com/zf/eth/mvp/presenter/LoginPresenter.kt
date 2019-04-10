package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.LoginContract
import com.zf.eth.mvp.model.LoginModel
import com.zf.eth.net.exception.ExceptionHandle

class LoginPresenter : BasePresenter<LoginContract.View>(), LoginContract.Presenter {


    private val model: LoginModel by lazy { LoginModel() }

    override fun requestLogin(mobile: String, pwd: String) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.login(mobile, pwd)
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when (it.status) {
                        1 -> setLogin(it.result)
                        else -> showError(it.result.message, it.status)
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