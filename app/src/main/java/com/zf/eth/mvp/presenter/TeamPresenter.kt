package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.TeamContract
import com.zf.eth.mvp.model.TeamModel
import com.zf.eth.net.exception.ExceptionHandle

class TeamPresenter : BasePresenter<TeamContract.View>(), TeamContract.Presenter {


    private val model: TeamModel by lazy { TeamModel() }

    private var mPage = 1

    override fun requestTeam(page: Int?) {
        checkViewAttached()

        mPage = page ?: mPage

        mRootView?.showLoading()
        val disposable = model.getTeam(mPage)
                .subscribe({
                    mRootView?.apply {

                        when (it.status) {
                            1 -> {
                                if (mPage == 1) {
                                    if (it.data.list.isNotEmpty()) {
                                        setTeam(it.data.list)
                                    } else {
                                        freshEmpty()
                                    }
                                } else {
                                    setLoadMore(it.data.list)
                                }
                                if (it.data.list.size < it.data.pagesize) {
                                    setLoadComplete()
                                }
                                mPage += 1
                            }
                            -1 -> {
                            }
                            else -> if (mPage == 1) showError(it.msg, it.status) else loadMoreError(it.msg, it.status)
                        }
                        dismissLoading()
                    }
                }, {
                    mRootView?.apply {
                        dismissLoading()
                        if (mPage == 1) showError(ExceptionHandle.handleException(it), ExceptionHandle.errorCode)
                        else loadMoreError(ExceptionHandle.handleException(it), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }
}