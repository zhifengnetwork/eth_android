package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter
import com.zf.eth.mvp.bean.BannerBean
import com.zf.eth.mvp.bean.HomeBean
import com.zf.eth.mvp.bean.NoticeList

interface HomeContract {

    interface View : IBaseView {

        fun showError(msg: String, errorCode: Int)

        fun setBanner(bean: List<BannerBean>)

        fun setNotice(bean: List<NoticeList>)

        fun setHome(bean:HomeBean)
    }

    interface Presenter : IPresenter<View> {
        //轮播图
        fun requestBanner()

        //公告
        fun requestNotice()

        //获取首页信息
        fun requestHome()

    }

}