package com.zf.eth.mvp.model

import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseBean
import com.zf.eth.mvp.bean.AppealListBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import com.zf.eth.utils.Preference
import io.reactivex.Observable

class AppealListModel{
    val userId by Preference(UriConstant.USER_ID, "")
    fun getAppealList(id:String): Observable<BaseBean<AppealListBean>> {
        return RetrofitManager.service.getAppealList(
            "member.androidapi.guamai_appeal_list",
            userId,
            id
        ).compose(SchedulerUtils.ioToMain())
    }
}