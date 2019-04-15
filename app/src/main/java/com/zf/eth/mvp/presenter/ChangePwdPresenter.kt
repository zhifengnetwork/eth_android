package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.ChangePwdContract
import com.zf.eth.mvp.model.ChangePwdModel
import com.zf.eth.net.exception.ExceptionHandle

class ChangePwdPresenter : BasePresenter<ChangePwdContract.View>(), ChangePwdContract.Presenter {

    private val model: ChangePwdModel by lazy { ChangePwdModel() }

    //修改密码
    override fun requestChangePwd(mobile: String, code: String, pwd: String) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.requestChangePwd(mobile, code, pwd)
                .subscribe({
                    mRootView?.apply {
                        dismissLoading()
                        when (it.status) {
                            1 -> setChangePwd()
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

    //获取验证码
    override fun requestVerifyCode(mobile: String, temp: String) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.requestVerifyCode(mobile, temp)
                .subscribe({
                    mRootView?.apply {
                        dismissLoading()
                        when (it.status) {
                            1 -> setVerifyCode()
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