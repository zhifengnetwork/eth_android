package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter
import com.zf.eth.mvp.bean.BetBean
import com.zf.eth.mvp.bean.GameHomeBean

interface BetContract {

    interface View : IBaseView {

        fun showError(msg: String, errorCode: Int)

        fun setBet(bean: BetBean)

        fun setConfirmBeat(msg: String)

        fun setGameHome(bean: GameHomeBean)

    }

    interface Presenter : IPresenter<View> {

        fun requestBet(type: Int, payment: Int?, money: String?, list: Array<Array<String>>?)

        fun requestConfirmBet(type: Int, payment: Int?, money: String?, list: Array<Array<String>>?)

        fun requestGameHome()
    }

}