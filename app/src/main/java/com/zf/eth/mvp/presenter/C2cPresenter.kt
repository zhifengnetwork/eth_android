package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.C2cContract
import com.zf.eth.mvp.model.C2cModel
import com.zf.eth.net.exception.ExceptionHandle

class C2cPresenter:BasePresenter<C2cContract.View>(),C2cContract.Presenter{
    private val model:C2cModel by lazy { C2cModel() }
    override fun requesC2c(page: String, status: String, type: String) {
         checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.getC2c(page,status,type)
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when(it.status){
                         1-> setC2c(it.data)
                        else -> showError("error", it.status)
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
