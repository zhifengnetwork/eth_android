package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter
import com.zf.eth.mvp.bean.HomeSetBean

interface HomeContract {

    interface View : IBaseView {

        fun showError(msg: String, errorCode: Int)

        fun setHome(bean: HomeSetBean)
    }

    interface Presenter : IPresenter<View> {

        //获取首页信息
        fun requestHome()

    }

}