package com.zf.eth.mvp.model

import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseBean
import com.zf.eth.mvp.bean.InvestBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import com.zf.eth.utils.Preference
import io.reactivex.Observable

class InvestModel {

    private val userId by Preference(UriConstant.USER_ID, "")

    fun getInvest(type: String, page: Int): Observable<BaseBean<InvestBean>> {
        return RetrofitManager.service.getInvest(
            if (type == "6") "member.androidapi.money_log"
            else "member.androidapi.investment_record",
            userId,
            type,
            page
        )
            .compose(SchedulerUtils.ioToMain())
    }


}