package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.BulletinDetailContract
import com.zf.eth.mvp.model.BulletinDetailModel
import com.zf.eth.net.exception.ExceptionHandle

class BulletinDetailPresenter : BasePresenter<BulletinDetailContract.View>(), BulletinDetailContract.Presenter {
    private val model by lazy { BulletinDetailModel() }
    override fun requseBulletinDetail(aid: String) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.getBulletinDetail(aid)
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    when (it.status) {
                        1 -> getBulletinDetail(it.data)
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

}