package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter

interface TransferContract {

    interface View : IBaseView {

        fun showError(msg: String, errorCode: Int)

        fun setTransfer(msg: String)

    }

    interface Presenter : IPresenter<View> {

        fun requestTransfer(money: String, id: String)


    }

}