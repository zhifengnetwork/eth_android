package com.zf.eth.mvp.contract

import com.zf.eth.base.BaseBean
import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter
import com.zf.eth.mvp.bean.C2cBean

interface C2cContract{
    interface View:IBaseView{
        fun showError(msg: String, errorCode: Int)
        fun setC2c(bean: C2cBean)
    }
    interface Presenter:IPresenter<View>{
        fun requesC2c(page:String,status:String,type:String)
    }
}