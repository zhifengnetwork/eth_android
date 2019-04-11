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
    private val openId by Preference(UriConstant.OPEN_ID, "")

    fun getInvest(type: String): Observable<BaseBean<List<InvestBean>>> {
        return RetrofitManager.service.getInvest(
            "index.total_investment",
            openId,
            userId,
            type
        )
            .compose(SchedulerUtils.ioToMain())
    }


}