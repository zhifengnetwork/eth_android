package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter
import com.zf.eth.mvp.bean.TeamBean

interface TeamContract {

    interface View : IBaseView {

        fun showError(msg: String, errorCode: Int)

        fun setTeam(bean: List<TeamBean>)

        fun freshEmpty()
    }

    interface Presenter : IPresenter<View> {

        fun requestTeam( )

    }

}