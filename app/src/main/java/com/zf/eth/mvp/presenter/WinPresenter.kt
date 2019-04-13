package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.WinContract
import com.zf.eth.mvp.model.WinModel
import com.zf.eth.net.exception.ExceptionHandle

class WinPresenter : BasePresenter<WinContract.View>(), WinContract.Presenter {


    private val model: WinModel by lazy { WinModel() }

    private var mPage = 1

    override fun requestWin(page: Int?) {

        mPage = page ?: mPage

        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.requestWin(mPage)
            .subscribe({
                mRootView?.apply {
                    when (it.status) {
                        1 -> {
                            if (mPage == 1) {
                                if (it.data.list.isNotEmpty()) {
                                    setWin(it.data.list)
                                } else {
                                    setEmpty()
                                }
                            } else {
                                setLoadMore(it.data.list)
                            }
                            if (it.data.list.size < it.data.pagesize) {
                                setLoadComplete()
                            }
                            mPage += 1
                        }
                        else -> if (mPage == 1) showError(it.msg, it.status) else loadError(it.msg, it.status)
                    }
                    dismissLoading()
                }
            }, {
                mRootView?.apply {
                    dismissLoading()
                    if (mPage == 1) showError(ExceptionHandle.handleException(it), ExceptionHandle.errorCode)
                    else loadError(ExceptionHandle.handleException(it), ExceptionHandle.errorCode)
                }
            })
        addSubscription(disposable)
    }
}