package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter
import com.zf.eth.mvp.bean.LoginResult

interface ChangePwdContract {

    interface View : IBaseView {

        fun showError(msg: String, errorCode: Int)

        fun setVerifyCode()

        fun setChangePwd()

    }

    interface Presenter : IPresenter<View> {

        fun requestVerifyCode(mobile: String, temp: String)

        fun requestChangePwd(mobile: String, code: String, pwd: String)

    }

}