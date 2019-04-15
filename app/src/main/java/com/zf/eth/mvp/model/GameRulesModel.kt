package com.zf.eth.mvp.model

import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import com.zf.eth.utils.Preference
import io.reactivex.Observable

class GameRulesModel {

    private val userId by Preference(UriConstant.USER_ID, "")

    fun requestGameRules(): Observable<BaseBean<String>> {

        return RetrofitManager.service.getGameRules(
            "member.androidapi.fucairule",
            userId
        )
            .compose(SchedulerUtils.ioToMain())
    }


}