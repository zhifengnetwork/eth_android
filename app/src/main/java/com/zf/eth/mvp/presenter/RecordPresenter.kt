package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.RecordContract
import com.zf.eth.mvp.model.RecordModel
import com.zf.eth.net.exception.ExceptionHandle

class RecordPresenter : BasePresenter<RecordContract.View>(), RecordContract.Presenter {


    private val model: RecordModel by lazy { RecordModel() }

    private var mPage = 1

    override fun requestRecord(page: Int?) {

        mPage = page ?: mPage

        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.requestRecord(mPage)
            .subscribe({
                mRootView?.apply {
                    when (it.status) {
                        1 -> {
                            if (mPage == 1) {
                                if (it.data.list.isNotEmpty()) {
                                    setRecord(it.data.list)
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
                        -1 -> {
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