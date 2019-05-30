package com.zf.eth.mvp.contract

import com.zf.eth.base.BaseBean
import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter
import com.zf.eth.mvp.bean.C2cBean
import com.zf.eth.mvp.bean.PayManageBean
import com.zf.eth.mvp.bean.TeamList

interface C2cContract{
    interface View:IBaseView{
        fun showError(msg: String, errorCode: Int)
        //c2c界面
        fun setC2c(bean: C2cBean)
        //C2c主页买入卖出按钮
        fun setSelloutSuccess(msg:String)
        //支付管理信息
        fun getPay(bean: PayManageBean)

        fun freshEmpty()

        fun setLoadMore(bean: C2cBean)

        fun setLoadComplete()

        fun loadMoreError(msg: String, errorCode: Int)

        fun setBuyError(msg: String)
    }
    interface Presenter:IPresenter<View>{

        fun requesC2c(page:Int?,type:String)
        fun requesC2cSellout(id:String,type:String)
        //支付管理信息
        fun requestPay()
    }
}