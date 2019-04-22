package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.C2cContract
import com.zf.eth.mvp.model.C2cModel
import com.zf.eth.net.exception.ExceptionHandle

class C2cPresenter : BasePresenter<C2cContract.View>(), C2cContract.Presenter {
    override fun requesC2cSellout(id: String, type: String) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.setC2cSellout(id, type)
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when (it.status) {
                        1 -> setSelloutSuccess(it.msg)
                        -1 -> {
                        }
                        else -> setBuyError(it.msg)
                    }
                }
            }, {
                mRootView?.apply {
                    dismissLoading()
                    setBuyError(ExceptionHandle.handleException(it))
                }
            })
        addSubscription(disposable)
    }

    private val model: C2cModel by lazy { C2cModel() }

    private var mPage = 1
    override fun requesC2c(page: Int?, type: String) {
        checkViewAttached()

        mPage = page ?: mPage

        mRootView?.showLoading()
        val disposable = model.getC2c(mPage, type)
            .subscribe({
                mRootView?.apply {
                    dismissLoading()

                    when (it.status) {
                        1 -> {
                            if (mPage == 1) {
                                if (it.data.list.isNotEmpty()) {
                                    setC2c(it.data)
                                } else {
                                    freshEmpty()
                                }
                            } else {
                                setLoadMore(it.data)
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
                    showError(ExceptionHandle.handleException(it), ExceptionHandle.errorCode)
                }

            })
        addSubscription(disposable)
    }

}
