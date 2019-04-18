package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter

interface ConfirmOrderContrect{
    interface View:IBaseView{
        fun showError(msg: String, errorCode: Int)
        fun setConfirmOrderSuccess(msg: String)
    }
    interface Presenter:IPresenter<View>{
        fun requestConfirmOrder(id:String,file:String)
    }
}