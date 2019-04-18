package com.zf.eth.mvp.model

import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import com.zf.eth.utils.Preference
import io.reactivex.Observable

class ComplainModel{
    private val userId by Preference(UriConstant.USER_ID, "")
    fun setComplain(id:String,files:String,text:String,textarea:String): Observable<BaseBean<Unit>>{
        return RetrofitManager.service.setComplain(
            "member.androidapi.guamai_appeal_add",
            userId,
            id,
            files,
            text,
            textarea
        ).compose(SchedulerUtils.ioToMain())
    }
}