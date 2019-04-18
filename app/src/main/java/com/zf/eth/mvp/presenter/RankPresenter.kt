package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.RankContract
import com.zf.eth.mvp.model.RankModel
import com.zf.eth.net.exception.ExceptionHandle

class RankPresenter : BasePresenter<RankContract.View>(), RankContract.Presenter {


    private val model: RankModel by lazy { RankModel() }

    override fun requestRank() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.requestRank()
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when (it.status) {
                        1 -> setRank(it.data)
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