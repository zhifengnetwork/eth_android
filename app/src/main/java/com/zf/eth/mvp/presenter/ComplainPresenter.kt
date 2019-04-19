package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.ComplainContract
import com.zf.eth.mvp.model.ComplainModel
import com.zf.eth.net.exception.ExceptionHandle

class ComplainPresenter:BasePresenter<ComplainContract.View>(),ComplainContract.Presrnter{
    private val model by lazy { ComplainModel() }
    override fun requesComplain(id: String, files: String, text: String, textarea: String) {
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