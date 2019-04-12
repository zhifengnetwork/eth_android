package com.zf.eth.mvp.model

import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseBean
import com.zf.eth.mvp.bean.EarnBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import com.zf.eth.utils.Preference
import io.reactivex.Observable

class EarnModel {

    private val openId by Preference(UriConstant.OPEN_ID, "")
    private val userId by Preference(UriConstant.USER_ID, "")

    fun requestEarn(type: String): Observable<BaseBean<EarnBean>> {
        return RetrofitManager.service.getEarn(
                "member.androidapi.income_record",
                openId,
                userId,
                type

        )
                .compose(SchedulerUtils.ioToMain())
    }


}