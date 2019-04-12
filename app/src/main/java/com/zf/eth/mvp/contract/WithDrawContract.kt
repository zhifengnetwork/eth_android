package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter

interface WithDrawContract {

    interface View : IBaseView {

        fun showError(msg: String, errorCode: Int)

        fun setWithDraw(msg:String)

    }

    interface Presenter : IPresenter<View> {

        fun requestWithDraw(money: String)

    }

}