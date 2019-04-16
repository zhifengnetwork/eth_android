package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter
import com.zf.eth.mvp.bean.BuyBean
import com.zf.eth.mvp.bean.LoginBean

interface BuyContract {

    interface View : IBaseView {

        fun showError(msg: String, errorCode: Int)

        fun setBuyInfo(bean: BuyBean)

        fun setPayImg(url:String)

        fun setConfirmPay()

    }

    interface Presenter : IPresenter<View> {

        fun requestBuyInfo()

        fun requestPayImg(file: String)

        fun requestConfirmPay(money: String, url: String)
    }

}