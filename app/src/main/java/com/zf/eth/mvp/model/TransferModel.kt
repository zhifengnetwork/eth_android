package com.zf.eth.mvp.model

import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import com.zf.eth.utils.Preference
import io.reactivex.Observable

class TransferModel {

    private val userId by Preference(UriConstant.USER_ID, "")

    fun requestTransfer(money: String, id: String): Observable<BaseBean<Unit>> {
        return RetrofitManager.service.requestTransfer(
            "member.androidapi.zhuangzhangis",
            userId,
            money,
            id
        )
            .compose(SchedulerUtils.ioToMain())
    }


}