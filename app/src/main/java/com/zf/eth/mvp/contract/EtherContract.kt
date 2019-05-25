package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter
import com.zf.eth.mvp.bean.Ether

interface EtherContract {
    interface View : IBaseView {
        fun showError(msg: String, errorCode: Int)

        fun getEther(bean: Ether)
    }

    interface Presenter : IPresenter<View> {
        fun requestEther()
    }
}