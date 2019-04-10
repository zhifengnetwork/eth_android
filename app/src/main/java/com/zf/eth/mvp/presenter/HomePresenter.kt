package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.HomeContract
import com.zf.eth.mvp.model.HomeModel
import com.zf.eth.net.exception.ExceptionHandle

class HomePresenter : BasePresenter<HomeContract.View>(), HomeContract.Presenter {


    private val model: HomeModel by lazy { HomeModel() }

    //首页轮播图
    override fun requestBanner() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.getBanner()
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when (it.status) {
                        1 -> setBanner(it.data)
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

    //首页公告
    override fun requestNotice() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.getNotice()
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when (it.status) {
                        1 -> setNotice(it.list)
                        else -> showError("error", it.status)
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

    override fun requestHome() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.getHome()
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when (it.status) {
                        1 -> setHome(it.data)
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