package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter
import com.zf.eth.mvp.bean.Ether

interface HangonsaleContract{
    interface View:IBaseView{
        fun showError(msg: String, errorCode: Int)
        fun setHangonsaleSuccess(msg:String)
        //获得手续费 参考价
        fun getEther(bean: Ether)
    }
    interface Presenter:IPresenter<View>{
        fun requesHangonsale(type:String,price:String,money:String,sxf0:String,trx:String,trx2:String)

        fun requestEther()
    }
}