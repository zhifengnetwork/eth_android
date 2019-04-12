package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter
import com.zf.eth.mvp.bean.BulletinBean

interface BulletinContract{
    interface View:IBaseView{
        fun showError(msg: String, errorCode: Int)
        fun getBulletin(bean: BulletinBean)
    }
    interface Presenter:IPresenter<View>{
        fun requseBulletin(page:String,cateId:String)
    }
}

