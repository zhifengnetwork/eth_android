package com.zf.eth.mvp.model

import com.zf.eth.mvp.bean.UserInfoBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import io.reactivex.Observable

class UserInfoModel {
    fun getUserInfo(): Observable<UserInfoBean> {
        return RetrofitManager.service.getUserInfo(
            "12",
            "entry",
            "ewei_shopv2",
            "mobile",
            "account.login"
        )
            .compose(SchedulerUtils.ioToMain())
    }


}