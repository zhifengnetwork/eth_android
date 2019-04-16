package com.zf.eth.mvp.contract

import com.zf.eth.base.BaseBean
import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter
import com.zf.eth.mvp.bean.C2cBean

interface C2cContract{
    interface View:IBaseView{
        fun showError(msg: String, errorCode: Int)
        //c2c界面
        fun setC2c(bean: C2cBean)
        //C2c买入卖出按钮
        fun setSelloutSuccess(msg:String)
    }
    interface Presenter:IPresenter<View>{

        fun requesC2c(page:String,type:String)
        fun requesC2cSellout(id:String,type:String)
    }
}