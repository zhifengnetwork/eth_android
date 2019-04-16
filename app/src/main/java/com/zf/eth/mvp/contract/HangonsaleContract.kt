package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter

interface HangonsaleContract{
    interface View:IBaseView{
        fun showError(msg: String, errorCode: Int)
        fun setHangonsaleSuccess(msg:String)
    }
    interface Presenter:IPresenter<View>{
        fun requesHangonsale(type:String,price:String,money:String,sxf0:String,trx:String,trx2:String)
    }
}