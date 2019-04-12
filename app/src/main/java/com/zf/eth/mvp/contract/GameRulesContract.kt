package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter

interface GameRulesContract {

    interface View : IBaseView {

        fun showError(msg: String, errorCode: Int)

        fun setGameRules(txt: String)

    }

    interface Presenter : IPresenter<View> {

        fun requestGameRules()

    }

}