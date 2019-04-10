package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.TeamContract
import com.zf.eth.mvp.model.TeamModel
import com.zf.eth.net.exception.ExceptionHandle

class TeamPresenter : BasePresenter<TeamContract.View>(), TeamContract.Presenter {


    private val model: TeamModel by lazy { TeamModel() }

    override fun requestTeam() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.getTeam()
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when (it.status) {
                        1 -> {
                            if (it.data.isNotEmpty()) {
                                setTeam(it.data)
                            } else {
                                freshEmpty()
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