package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.DownloadContract
import com.zf.eth.mvp.model.DownloadModel
import com.zf.eth.net.exception.ExceptionHandle

class DownloadPresenter:BasePresenter<DownloadContract.View>(),DownloadContract.Presenter{
    private val model by lazy { DownloadModel() }
    override fun requestDownload() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.getDownload()
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when (it.status) {
                        1 -> getDownload(it.data)
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