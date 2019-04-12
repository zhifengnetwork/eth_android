package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.BetContract
import com.zf.eth.mvp.model.BetModel
import com.zf.eth.net.exception.ExceptionHandle

class BetPresenter : BasePresenter<BetContract.View>(), BetContract.Presenter {

    private val model: BetModel by lazy { BetModel() }

    override fun requestBet(type: Int, payment: Int?, money: String?, list: Array<Array<String>>?) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.requestBet(type, payment, money, list)
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when (it.status) {
                        1 -> setBet(it.data)
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