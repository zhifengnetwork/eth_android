package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.InvestContract
import com.zf.eth.mvp.model.InvestModel
import com.zf.eth.net.exception.ExceptionHandle

class InvestPresenter : BasePresenter<InvestContract.View>(), InvestContract.Presenter {


    private val model: InvestModel by lazy { InvestModel() }

    override fun requestInvest(type: String) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.getInvest(type)
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when (it.status) {
                        1 -> {
                            if (it.data.isNotEmpty()) {
                                setInvest(it.data)
                            } else {
                                setEmpty()
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