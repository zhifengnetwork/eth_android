package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.ComplainContract
import com.zf.eth.mvp.model.ComplainModel
import com.zf.eth.net.exception.ExceptionHandle

class ComplainPresenter:BasePresenter<ComplainContract.View>(),ComplainContract.Presrnter{
    override fun requestPayImg(file: String) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.requestPayImg(file)
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when (it.status) {
                        1 -> setPayImg(it.data.img)
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

    private val model by lazy { ComplainModel() }
    override fun requestComplain(id: String, files: String, text: String, textarea: String) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.setComplain(id,files,text,textarea)
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when(it.status){
                        1 -> setComplainSuccess(it.msg)
                        else -> showError(it.msg, it.status)
                    }
                }
            },{
                mRootView?.apply {
                    dismissLoading()
                    showError(ExceptionHandle.handleException(it), ExceptionHandle.errorCode)
                }
            })
        addSubscription(disposable)
    }

}