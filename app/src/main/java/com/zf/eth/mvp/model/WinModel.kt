package com.zf.eth.mvp.model

import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseBean
import com.zf.eth.mvp.bean.WinBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import com.zf.eth.utils.Preference
import io.reactivex.Observable

class WinModel {

    private val userId by Preference(UriConstant.USER_ID, "")

    fun requestWin(page: Int?): Observable<BaseBean<WinBean>> {
        return RetrofitManager.service.requestWin(
            "member.androidapi.winningrecordis",
            userId,
            page
        )
            .compose(SchedulerUtils.ioToMain())
    }

}