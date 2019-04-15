package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter
import com.zf.eth.mvp.bean.WinList

interface WinContract {

    interface View : IBaseView {

        fun showError(msg: String, errorCode: Int)

        fun setWin(bean: List<WinList>)

        fun setLoadMore(bean: List<WinList>)

        fun setEmpty()

        fun setLoadComplete()

        fun loadError(msg: String, errorCode: Int)
    }

    interface Presenter : IPresenter<View> {

        fun requestWin(page: Int?)

    }

}