package com.zf.eth.mvp.model

import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseBean
import com.zf.eth.net.HomeRetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import com.zf.eth.utils.Preference
import io.reactivex.Observable

class LogOutModel {

    private val userId by Preference(UriConstant.USER_ID, "")

    fun requestLogOut(money:String): Observable<BaseBean<Unit>> {
        return HomeRetrofitManager.service.requestLogOut(
                "member.androidapi.out",
                userId,
                money
        )
                .compose(SchedulerUtils.ioToMain())
    }

}