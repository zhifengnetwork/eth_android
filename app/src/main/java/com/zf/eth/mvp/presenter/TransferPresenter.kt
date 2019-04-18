package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.TransferContract
import com.zf.eth.mvp.model.TransferModel
import com.zf.eth.net.exception.ExceptionHandle

class TransferPresenter : BasePresenter<TransferContract.View>(), TransferContract.Presenter {

    private val model: TransferModel by lazy { TransferModel() }

    //请求转账动作
    override fun requestTransfer(money: String, id: String) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.requestTransfer(money, id)
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when (it.status) {
                        1 -> setTransfer(it.msg)
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