package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter

interface ComplainContract{
    interface View:IBaseView{
        fun showError(msg: String, errorCode: Int)

        fun setComplainSuccess(msg: String)

        fun setPayImg(url:String)
    }
    interface Presrnter:IPresenter<View>{
        fun requestComplain(id:String, files:String, text:String, textarea:String)

        fun requestPayImg(file: String)
    }
}