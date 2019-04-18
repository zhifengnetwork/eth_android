package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter

interface ChangeInfoContract {

    interface View : IBaseView {

        fun showError(msg: String, errorCode: Int)

        fun setHead(url: String)

        fun setChangeInfo()

    }

    interface Presenter : IPresenter<View> {

        fun requestUpHead(file: String)

        fun requestChangeName(nickname: String, avatar: String)

    }

}