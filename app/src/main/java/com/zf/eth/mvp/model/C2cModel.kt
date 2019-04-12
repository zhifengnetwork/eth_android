package com.zf.eth.mvp.model

import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseBean
import com.zf.eth.mvp.bean.C2cBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import com.zf.eth.utils.Preference
import io.reactivex.Observable

class C2cModel{
    private val userId by Preference(UriConstant.USER_ID, "")
    fun getC2c(page: String,status: String,type: String):Observable<BaseBean<C2cBean>>{
        return RetrofitManager.service.getC2c(
            "member.androidapi.guamairecordjilu",
            userId,
            page,
            status,
            type
        ).compose(SchedulerUtils.ioToMain())
    }
}