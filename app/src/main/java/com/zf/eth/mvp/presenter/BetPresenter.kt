package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.BetContract
import com.zf.eth.mvp.model.BetModel
import com.zf.eth.net.exception.ExceptionHandle

class BetPresenter : BasePresenter<BetContract.View>(), BetContract.Presenter {

    private val model: BetModel by lazy { BetModel() }

    //1.确认信息
    override fun requestBet(type: Int, payment: Int?, money: String?, list: String?) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.requestBet(type, payment, money, list)
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when (it.status) {
                        1 -> if (type == 1) setBet(it.data)
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

    //2.确认下注
    override fun requestConfirmBet(type: Int, payment: Int?, money: String?, list: String?) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.requestConfirmBet(type, payment, money, list)
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when (it.status) {
                        1 -> setConfirmBeat(it.msg)
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

    //开奖号码 每注金额
    override fun requestGameHome() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.requestGameHome()
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when (it.status) {
                        1 -> setGameHome(it.data)
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