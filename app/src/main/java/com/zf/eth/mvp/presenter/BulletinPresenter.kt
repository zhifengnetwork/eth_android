package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.BulletinContract
import com.zf.eth.mvp.model.BulletinModel
import com.zf.eth.net.exception.ExceptionHandle

class BulletinPresenter:BasePresenter<BulletinContract.View>(),BulletinContract.Presenter{
    private val model by lazy { BulletinModel() }
    override fun requseBulletin(page: String, cateId: String) {
         checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.getBulletin(page,cateId)
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when(it.status){
                        1-> getBulletin(it.data)
                        -1 -> {
                        }
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