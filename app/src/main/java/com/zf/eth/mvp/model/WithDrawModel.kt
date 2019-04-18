package com.zf.eth.mvp.model

import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import com.zf.eth.utils.Preference
import io.reactivex.Observable

class WithDrawModel {

    private val userId by Preference(UriConstant.USER_ID, "")

    fun requestWithDraw(money: String): Observable<BaseBean<Unit>> {
        return RetrofitManager.service.requestWithDraw(
            "member.androidapi.submit",
            userId,
            money
        )
            .compose(SchedulerUtils.ioToMain())
    }


}