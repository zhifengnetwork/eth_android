package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter
import com.zf.eth.mvp.bean.InviteBean

interface InviteContract{
    interface View:IBaseView{
        fun showError(msg: String, errorCode: Int)

        fun getInvite(bean:InviteBean)
    }
    interface Presenter:IPresenter<View>{
        fun requestInvite()
    }
}