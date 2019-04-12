package com.zf.eth.mvp.contract


import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter
import com.zf.eth.mvp.bean.DownloadBean

interface DownloadContract{
    interface View:IBaseView{
        fun showError(msg: String, errorCode: Int)
        fun getDownload(bean: DownloadBean)
    }
    interface Presenter:IPresenter<View>{
        fun requestDownload()
    }
}