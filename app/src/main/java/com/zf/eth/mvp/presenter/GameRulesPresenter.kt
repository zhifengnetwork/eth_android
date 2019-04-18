package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.GameRulesContract
import com.zf.eth.mvp.model.GameRulesModel
import com.zf.eth.net.exception.ExceptionHandle

class GameRulesPresenter : BasePresenter<GameRulesContract.View>(), GameRulesContract.Presenter {


    private val model: GameRulesModel by lazy { GameRulesModel() }

    override fun requestGameRules() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.requestGameRules()
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when (it.status) {
                        1 -> setGameRules(it.data)
                        -1 -> {
                        }
                        else -> showError("err", it.status)
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