package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.WithDrawContract
import com.zf.eth.mvp.model.WithDrawModel
import com.zf.eth.net.exception.ExceptionHandle

class WithDrawPresenter : BasePresenter<WithDrawContract.View>(), WithDrawContract.Presenter {


    private val model: WithDrawModel by lazy { WithDrawModel() }

    override fun requestWithDraw(money: String) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.requestWithDraw(money)
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when (it.status) {
                        1 -> setWithDraw(it.msg)
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