package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.RegisterContract
import com.zf.eth.mvp.model.RegisterModel
import com.zf.eth.net.exception.ExceptionHandle

class RegisterPresenter : BasePresenter<RegisterContract.View>(), RegisterContract.Presenter {


    private val model: RegisterModel by lazy { RegisterModel() }

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

    override fun requestRegister(type: String, mobile: String, code: String, pwd: String,agentId:String) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.requestRegister(type, mobile, code, pwd,agentId)
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when (it.status) {
                        1 -> {
                            if (type == "sms_reg") {
                                setRegister(it.data)
                            } else if (type == "sms_changepwd") {
                                setForgetPwd()
                            }
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
}