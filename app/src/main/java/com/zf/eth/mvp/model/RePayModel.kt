package com.zf.eth.mvp.model

import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import com.zf.eth.utils.Preference
import io.reactivex.Observable

class RePayModel {

    private val userId by Preference(UriConstant.USER_ID, "")

    fun requestRePay(money: String, type: String): Observable<BaseBean<List<Unit>>> {
        return RetrofitManager.service.requestRePay(
                "member.androidapi.yijianfutou",
                userId,
                money,
                type
        )
                .compose(SchedulerUtils.ioToMain())
    }

}