package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.EarnContract
import com.zf.eth.mvp.model.EarnModel
import com.zf.eth.net.exception.ExceptionHandle

class EarnPresenter : BasePresenter<EarnContract.View>(), EarnContract.Presenter {

    private val model: EarnModel by lazy { EarnModel() }

    override fun requestEarn(type: String, dateType: String) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.requestEarn(type, dateType)
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when (it.status) {
                        1 -> setEarn(it.data)
                        -1 -> {
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