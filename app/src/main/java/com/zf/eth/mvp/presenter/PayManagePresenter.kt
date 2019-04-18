package com.zf.eth.mvp.presenter

import com.zf.eth.base.BasePresenter
import com.zf.eth.mvp.contract.PayManageContract
import com.zf.eth.mvp.model.PayManageModel
import com.zf.eth.net.exception.ExceptionHandle
import com.zf.eth.ui.activity.PaymentActivity

class PayManagePresenter : BasePresenter<PayManageContract.View>(), PayManageContract.Presenter {


    private val model by lazy { PayManageModel() }

    //上传图片
    override fun requestUpImg(file: String, type: String?) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.requestUpImg(file)
                .subscribe({
                    mRootView?.apply {
                        dismissLoading()
                        when (it.status) {
                            1 -> when (type) {
                                PaymentActivity.ALIPAYIMG -> setAlipayImg(it.data.img)
                                PaymentActivity.WECHATIMG -> setWechatImg(it.data.img)
                                else -> setAddressImg(it.data.img)
                            }
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


    //支付管理信息
    override fun requestPay() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.getPay()
                .subscribe({
                    mRootView?.apply {
                        dismissLoading()
                        when (it.status) {
                            1 -> getPay(it.data)
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

    //请求修改地址
    override fun requestEditPayManege(
            adress: String,
            url: String,
            zfbfile: String,
            wxfile: String,
            bankid: String,
            bankname: String,
            bank: String
    ) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = model.setPay(adress, url, zfbfile, wxfile, bankid, bankname, bank)
                .subscribe({
                    mRootView?.apply {
                        dismissLoading()
                        when (it.status) {
                            1 -> editPaySuccess()
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