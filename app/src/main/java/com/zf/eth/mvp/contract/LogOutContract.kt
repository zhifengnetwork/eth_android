package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter

interface LogOutContract {

    interface View : IBaseView {

        fun showError(msg: String, errorCode: Int)

        fun setLogOut()

    }

    interface Presenter : IPresenter<View> {

        fun requestLogOut(money:String)

    }

}