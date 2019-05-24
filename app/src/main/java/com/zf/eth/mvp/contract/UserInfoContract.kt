package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter
import com.zf.eth.mvp.bean.UserInfoBean

interface UserInfoContract {

    interface View : IBaseView {

        fun showError(msg: String, errorCode: Int)

        fun setUserInfo(bean: UserInfoBean)

        fun setNotLogin()
    }

    interface Presenter : IPresenter<View> {

        fun requestUserInfo()

        fun requestUserLevel()
    }

}