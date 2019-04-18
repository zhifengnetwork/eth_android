package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.ChangeInfoContract
import com.zf.eth.mvp.model.ChangeInfoModel
import com.zf.eth.net.exception.ExceptionHandle

class ChangeInfoPresenter : BasePresenter<ChangeInfoContract.View>(), ChangeInfoContract.Presenter {


    private val model: ChangeInfoModel by lazy { ChangeInfoModel() }

    override fun requestUpHead(file: String) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.requestUpHead(file)
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when (it.status) {
                        1 -> setHead(it.data.img)
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

    //修改信息
    override fun requestChangeName(nickname: String, avatar: String) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.requestChangeInfo(nickname, avatar)
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when (it.status) {
                        1 -> setChangeInfo()
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