package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter
import com.zf.eth.mvp.bean.RePayInfoBean

interface RePayContract {

    interface View : IBaseView {

        fun showError(msg: String, errorCode: Int)

        fun setRePay()

        fun setRePayInfo(bean: RePayInfoBean)

    }

    interface Presenter : IPresenter<View> {

        fun requestRePay(money: String, type: String)

        fun requestRePayInfo()
    }

}