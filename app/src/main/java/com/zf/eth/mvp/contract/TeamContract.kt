package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter
import com.zf.eth.mvp.bean.TeamList

interface TeamContract {

    interface View : IBaseView {

        fun showError(msg: String, errorCode: Int)

        fun setTeam(bean: List<TeamList>)

        fun freshEmpty()

        fun setLoadMore(bean: List<TeamList>)

        fun setLoadComplete()

        fun loadMoreError(msg: String, errorCode: Int)
    }

    interface Presenter : IPresenter<View> {

        fun requestTeam(page:Int?)

    }

}