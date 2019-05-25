package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.EtherContract
import com.zf.eth.mvp.model.EtherModel
import com.zf.eth.net.exception.ExceptionHandle

class EtherPresenter : BasePresenter<EtherContract.View>(), EtherContract.Presenter {
    private val model by lazy { EtherModel() }
    override fun requestEther() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.getEther()
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when (it.status) {
                        1 -> getEther(it.data.list)
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