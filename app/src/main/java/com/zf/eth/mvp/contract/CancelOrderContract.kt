package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter

interface CancelOrderContract{
    interface View:IBaseView{
        fun showError(msg: String, errorCode: Int)
        fun CancelOrderSuccess(msg: String)
    }
    interface Presenter:IPresenter<View>{
        fun requesCancelOrder(id:String)
    }
}