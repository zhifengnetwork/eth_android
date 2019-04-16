package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.RePayContract
import com.zf.eth.mvp.model.RePayModel
import com.zf.eth.net.exception.ExceptionHandle

class RePayPresenter : BasePresenter<RePayContract.View>(), RePayContract.Presenter {


    private val model: RePayModel by lazy { RePayModel() }

    override fun requestRePay(money: String, type: String) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.requestRePay(money, type)
                .subscribe({
                    mRootView?.apply {
                        dismissLoading()
                        when (it.status) {
                            1 -> setRePay()
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