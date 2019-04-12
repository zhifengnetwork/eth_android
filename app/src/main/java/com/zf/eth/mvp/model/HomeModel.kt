package com.zf.eth.mvp.model

import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseBean
import com.zf.eth.mvp.bean.HomeSetBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import com.zf.eth.utils.Preference
import io.reactivex.Observable

class HomeModel {

    private val userId by Preference(UriConstant.USER_ID, "")

    fun getHome(): Observable<BaseBean<HomeSetBean>> {
        return RetrofitManager.service.getHome(
            userId
        ).compose(SchedulerUtils.ioToMain())
    }
}