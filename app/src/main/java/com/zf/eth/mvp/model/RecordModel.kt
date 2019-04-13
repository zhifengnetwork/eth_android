package com.zf.eth.mvp.model

import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseBean
import com.zf.eth.mvp.bean.RecordBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import com.zf.eth.utils.Preference
import io.reactivex.Observable

class RecordModel {

    private val userId by Preference(UriConstant.USER_ID, "")

    fun requestRecord(page: Int?): Observable<BaseBean<RecordBean>> {
        return RetrofitManager.service.requestRecord(
            "member.androidapi.stakejiluis",
            userId,
            UriConstant.PER_PAGE,
            page
        )
            .compose(SchedulerUtils.ioToMain())
    }

}