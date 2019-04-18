package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter
import com.zf.eth.mvp.bean.AppealBean
import com.zf.eth.mvp.bean.AppealListBean

interface AppealContract{
    interface View:IBaseView{
        fun showError(msg: String, errorCode: Int)
        fun getAppeal(bean: AppealBean)
    }
    interface Presenter:IPresenter<View>{
        fun requestAppeal(page:String)
    }
}