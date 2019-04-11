package com.zf.eth.mvp.model

import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseBean
import com.zf.eth.mvp.bean.BannerBean
import com.zf.eth.mvp.bean.HomeBean
import com.zf.eth.mvp.bean.NoticeBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import com.zf.eth.utils.Preference
import io.reactivex.Observable

class HomeModel {


    fun getBanner(): Observable<BaseBean<List<BannerBean>>> {
        return RetrofitManager.service.getBanner("index.lunbo")
            .compose(SchedulerUtils.ioToMain())
    }

    fun getNotice(): Observable<NoticeBean> {
        return RetrofitManager.service.getNotice("index.notice")
            .compose(SchedulerUtils.ioToMain())
    }


    private val openId by Preference(UriConstant.OPEN_ID, "")
    private val userId by Preference(UriConstant.USER_ID, "")

    fun getHome(): Observable<BaseBean<HomeBean>> {
        return RetrofitManager.service.getHome(
            "index.homeinfo", openId, userId
        )
            .compose(SchedulerUtils.ioToMain())
    }
}