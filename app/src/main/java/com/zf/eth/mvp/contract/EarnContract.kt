package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter
import com.zf.eth.mvp.bean.EarnList

interface EarnContract {

    interface View : IBaseView {

        fun showError(msg: String, errorCode: Int)

        fun setEarn(bean: List<EarnList>)

    }

    interface Presenter : IPresenter<View> {

        fun requestEarn(type: String)

    }

}