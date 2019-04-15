package com.zf.eth.mvp.model

import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseBean
import com.zf.eth.mvp.bean.TeamBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import com.zf.eth.utils.Preference
import io.reactivex.Observable

class TeamModel {

    private val userId by Preference(UriConstant.USER_ID, "")

    fun getTeam(page: Int): Observable<BaseBean<TeamBean>> {
        return RetrofitManager.service.getTeam(
                "member.androidapi.xiaji_get_list", userId, page
        )
                .compose(SchedulerUtils.ioToMain())
    }

}