package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter

interface ComplainContract{
    interface View:IBaseView{
        fun showError(msg: String, errorCode: Int)
        fun setComplainSuccess(msg: String)
    }
    interface Presrnter:IPresenter<View>{
        fun requesComplain(id:String,files:String,text:String,textarea:String)
    }
}