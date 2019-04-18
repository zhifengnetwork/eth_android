package com.zf.eth.mvp.model

import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseBean
import com.zf.eth.mvp.bean.AppealBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import com.zf.eth.utils.Preference
import io.reactivex.Observable

class AppealModel{
    val userId by Preference(UriConstant.USER_ID, "")
    fun getAppeal(page:String): Observable<BaseBean<AppealBean>> {
        return RetrofitManager.service.getAppeal(
            "member.androidapi.guamai_appeal",
            userId,
            page
        ).compose(SchedulerUtils.ioToMain())
    }
}