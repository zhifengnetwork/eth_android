package com.zf.eth.mvp.model

import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import com.zf.eth.utils.Preference
import io.reactivex.Observable

class HangonsaleModel{
    private val userId by Preference(UriConstant.USER_ID, "")
    fun setHangonsale(type:String,price:String,money:String,sxf0:String,trx:String,trx2:String): Observable<BaseBean<Unit>>{
        return RetrofitManager.service.setHangonsale(
            "member.androidapi.hangonsale",
            userId,
            type,
            price,
            money,
            sxf0,
            trx,
            trx2
        ).compose(SchedulerUtils.ioToMain())
    }
}