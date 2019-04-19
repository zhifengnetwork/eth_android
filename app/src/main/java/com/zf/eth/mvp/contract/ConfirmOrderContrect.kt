package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter
import com.zf.eth.mvp.bean.OrderDetailBean

interface ConfirmOrderContrect{
    interface View:IBaseView{
        fun showError(msg: String, errorCode: Int)

        fun setConfirmOrderSuccess(msg: String)

        fun setPayImg(url:String)
        //获取订单详情
        fun getOrderDetail(bean: OrderDetailBean)
    }
    interface Presenter:IPresenter<View>{
        fun requestConfirmOrder(id:String,file:String)

        fun requestPayImg(file: String)

        fun requestOrderDetail(id: String)
    }
}