package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.UserInfoContract
import com.zf.eth.mvp.model.UserInfoModel
import com.zf.eth.net.exception.ExceptionHandle

class UserInfoPresenter : BasePresenter<UserInfoContract.View>(), UserInfoContract.Presenter {


    private val model: UserInfoModel by lazy { UserInfoModel() }

    override fun requestUserInfo() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.getUserInfo()
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when (it.status) {
                        1 -> setUserInfo(it.data)
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