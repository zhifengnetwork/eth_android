package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter
import com.zf.eth.mvp.bean.AppealList
import com.zf.eth.mvp.bean.AppealListBean

interface AppealListContract{
    interface View:IBaseView{
        fun showError(msg: String, errorCode: Int)
        fun getAppealList(bean: AppealListBean)
    }
    interface Presenter:IPresenter<View>{
        fun requestAppealList(id:String)
    }
}