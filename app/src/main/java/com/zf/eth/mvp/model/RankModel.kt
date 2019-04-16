package com.zf.eth.mvp.model

import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseBean
import com.zf.eth.mvp.bean.RankBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import com.zf.eth.utils.Preference
import io.reactivex.Observable

class RankModel {

    private val userId by Preference(UriConstant.USER_ID, "")

    fun requestRank(): Observable<BaseBean<RankBean>> {
        return RetrofitManager.service.requestRank(
                "member.androidapi.fucairanking",
                userId
        )
                .compose(SchedulerUtils.ioToMain())
    }

}