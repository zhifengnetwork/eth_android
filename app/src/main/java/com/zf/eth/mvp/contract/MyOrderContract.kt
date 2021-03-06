package com.zf.eth.mvp.contract


import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter
import com.zf.eth.mvp.bean.MyOrderBean

interface MyOrderContract{
    interface View:IBaseView{
        fun showError(msg: String, errorCode: Int)
        fun getMyOrder(bean: MyOrderBean)
    }
    interface Presenter:IPresenter<View>{
        fun requestMyOrder(status:String)
    }
}