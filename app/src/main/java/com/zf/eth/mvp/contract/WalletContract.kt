package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter
import com.zf.eth.mvp.bean.ChargeBean
import com.zf.eth.mvp.bean.WalletBean

interface WalletContract {

    interface View : IBaseView {

        fun showError(msg: String, errorCode: Int)

        fun setWallet(bean: WalletBean)

        fun setChart(bean: ChargeBean)
    }

    interface Presenter : IPresenter<View> {

        fun requestWallet( )

        fun requestCharge()
    }

}