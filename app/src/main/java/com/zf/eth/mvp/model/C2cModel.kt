package com.zf.eth.mvp.model

import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseBean
import com.zf.eth.mvp.bean.C2cBean
import com.zf.eth.mvp.bean.PayManageBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import com.zf.eth.utils.Preference
import io.reactivex.Observable

class C2cModel {
    private val userId by Preference(UriConstant.USER_ID, "")

    fun getC2c(page: Int, type: String): Observable<BaseBean<C2cBean>> {
        return RetrofitManager.service.getC2c(
            "member.androidapi.guamairecordjilu",
            userId,
            page,
            type
        ).compose(SchedulerUtils.ioToMain())
    }

    fun setC2cSellout(id: String, type: String): Observable<BaseBean<Unit>> {
        return RetrofitManager.service.setSellout(
            "member.androidapi.sellout",
            userId,
            id,
            type
        ).compose(SchedulerUtils.ioToMain())
    }

    //支付管理信息
    fun getPay(): Observable<BaseBean<PayManageBean>> {
        return RetrofitManager.service.getPayManage("member.androidapi.pay_management", userId)
            .compose(SchedulerUtils.ioToMain())
    }

}