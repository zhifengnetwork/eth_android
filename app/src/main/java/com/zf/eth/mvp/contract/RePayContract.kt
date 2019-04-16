package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter

interface RePayContract {

    interface View : IBaseView {

        fun showError(msg: String, errorCode: Int)

        fun setRePay()

    }

    interface Presenter : IPresenter<View> {

        fun requestRePay(money: String, type: String)

    }

}