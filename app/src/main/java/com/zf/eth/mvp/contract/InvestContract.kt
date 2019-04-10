package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter
import com.zf.eth.mvp.bean.InvestBean
import com.zf.eth.mvp.bean.LoginResult

interface InvestContract {

    interface View : IBaseView {

        fun showError(msg: String, errorCode: Int)

        fun setInvest(bean: List<InvestBean>)

        fun setEmpty()

    }

    interface Presenter : IPresenter<View> {

        fun requestInvest(type: String)

    }

}