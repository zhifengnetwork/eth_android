package com.zf.eth.mvp.model

import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import com.zf.eth.utils.Preference
import io.reactivex.Observable

class CancelOrderModel{
    private val userId by Preference(UriConstant.USER_ID, "")
    fun requesCancelOrder(id:String): Observable<BaseBean<Unit>>{
        return RetrofitManager.service.requestCancelOrder(
            "member.androidapi.sellout_tab_con",
            userId,
            id
        ).compose(SchedulerUtils.ioToMain())
    }
}