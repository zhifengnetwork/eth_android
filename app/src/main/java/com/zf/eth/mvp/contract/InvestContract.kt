package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter
import com.zf.eth.mvp.bean.InvestList

interface InvestContract {

    interface View : IBaseView {

        fun showError(msg: String, errorCode: Int)

        fun loadError(msg: String, errorCode: Int)

        fun setInvest(bean: List<InvestList>)

        fun setLoadMore(bean: List<InvestList>)

        fun setLoadComplete()

        fun setEmpty()

    }

    interface Presenter : IPresenter<View> {

        fun requestInvest(type: String, page: Int?)

    }

}