package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter
import com.zf.eth.mvp.bean.RegisterBean

interface RegisterContract {

    interface View : IBaseView {

        fun showError(msg: String, errorCode: Int)

        fun setRegister(bean: RegisterBean)

        fun setForgetPwd()

        fun setVerifyCode()

    }

    interface Presenter : IPresenter<View> {

        fun requestRegister(type: String, mobile: String, code: String, pwd: String, agentId: String)

        fun requestVerifyCode(mobile: String, temp: String)
    }

}