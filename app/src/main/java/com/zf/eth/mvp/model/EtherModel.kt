package com.zf.eth.mvp.model

import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseBean
import com.zf.eth.mvp.bean.EtherBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import com.zf.eth.utils.Preference
import io.reactivex.Observable

class EtherModel {
    private val userId by Preference(UriConstant.USER_ID, "")
    fun getEther(): Observable<BaseBean<EtherBean>> {
        return RetrofitManager.service.getEther("member.androidapi.ether", userId)
            .compose(SchedulerUtils.ioToMain())
    }
}