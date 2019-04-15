package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter
import com.zf.eth.mvp.bean.LoginBean

interface LoginContract {

    interface View : IBaseView {

        fun showError(msg: String, errorCode: Int)

        fun setLogin(bean: LoginBean)

    }

    interface Presenter : IPresenter<View> {

        fun requestLogin(mobile: String, pwd: String)

    }

}