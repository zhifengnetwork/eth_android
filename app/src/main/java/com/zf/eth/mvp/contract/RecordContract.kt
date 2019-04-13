package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter
import com.zf.eth.mvp.bean.RecordList

interface RecordContract {

    interface View : IBaseView {

        fun showError(msg: String, errorCode: Int)

        fun setRecord(bean: List<RecordList>)

        fun setLoadMore(bean: List<RecordList>)

        fun setEmpty()

        fun setLoadComplete()

        fun loadError(msg: String, errorCode: Int)
    }

    interface Presenter : IPresenter<View> {

        fun requestRecord(page: Int?)

    }

}