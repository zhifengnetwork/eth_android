package com.zf.eth.mvp.model

import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseBean

import com.zf.eth.mvp.bean.InviteBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import com.zf.eth.utils.Preference
import io.reactivex.Observable

class InviteModel {

    private val userId by Preference(UriConstant.USER_ID, "")

    fun getInvite(): Observable<BaseBean<InviteBean>> {
        return RetrofitManager.service.getInvite("commission.qrcode", userId)
                .compose(SchedulerUtils.ioToMain())
    }
}