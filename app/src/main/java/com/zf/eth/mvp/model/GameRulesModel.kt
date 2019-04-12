package com.zf.eth.mvp.model

import com.zf.eth.api.UriConstant
import com.zf.eth.mvp.bean.GameRulesBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import com.zf.eth.utils.Preference
import io.reactivex.Observable

class GameRulesModel {

    private val userId by Preference(UriConstant.USER_ID, "")
    private val openId by Preference(UriConstant.OPEN_ID, "")

    fun requestGameRules(): Observable<GameRulesBean> {

        return RetrofitManager.service.getGameRules(
            "member.androidapi.fucairule",
            openId,
            userId
        )
            .compose(SchedulerUtils.ioToMain())
    }


}