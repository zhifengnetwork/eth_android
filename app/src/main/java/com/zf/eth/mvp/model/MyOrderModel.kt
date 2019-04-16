package com.zf.eth.mvp.model

import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseBean
import com.zf.eth.mvp.bean.MyOrderBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import com.zf.eth.utils.Preference
import io.reactivex.Observable

class MyOrderModel{
    private val userId by Preference(UriConstant.USER_ID, "")
    fun getMyOrder(status:String): Observable<BaseBean<List<MyOrderBean>>> {
        return RetrofitManager.service.getMyOrder(
            "member.androidapi.number_order",
            userId,
            status
        ).compose(SchedulerUtils.ioToMain())
    }
}