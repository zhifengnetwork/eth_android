package com.zf.eth.mvp.model

import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseBean
import com.zf.eth.mvp.bean.BetBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import com.zf.eth.utils.Preference
import io.reactivex.Observable

class BetModel {


    val userId by Preference(UriConstant.USER_ID, "")

    fun requestBet(type: Int, payment: Int?, money: String?, list: Array<Array<String>>?): Observable<BaseBean<BetBean>> {


        return RetrofitManager.service.requestBet(
            "member.androidapi.bets",
            userId,
            type,
            payment, money, list
        )
            .compose(SchedulerUtils.ioToMain())
    }


}