package com.zf.eth.mvp.contract

import com.zf.eth.base.BaseBean
import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter
import com.zf.eth.mvp.bean.OrderDetailBean

interface CancelOrderContract{
    interface View:IBaseView{
        fun showError(msg: String, errorCode: Int)
        //取消订单
        fun CancelOrderSuccess(msg: String)
        //获取订单详情
        fun getOrderDetail(bean: OrderDetailBean)
    }
    interface Presenter:IPresenter<View>{
        fun requestCancelOrder(id:String)

        fun requestOrderDetail(id: String)
    }

}