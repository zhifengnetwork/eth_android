package com.zf.eth.mvp.model

import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseBean
import com.zf.eth.mvp.bean.TeamBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import com.zf.eth.utils.Preference
import io.reactivex.Observable

class TeamModel {

    private val openId by Preference(UriConstant.OPEN_ID, "")
    private val userId by Preference(UriConstant.USER_ID, "")

    fun getTeam(): Observable<BaseBean<List<TeamBean>>> {
        return RetrofitManager.service.getTeam(
            "index.xiaji", openId, userId
        )
            .compose(SchedulerUtils.ioToMain())
    }


}