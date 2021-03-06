package com.zf.eth.mvp.model

import com.zf.eth.base.BaseBean
import com.zf.eth.mvp.bean.LoginBean
import com.zf.eth.net.HomeRetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import io.reactivex.Observable

class LoginModel {
    fun login(mobile: String, pwd: String): Observable<BaseBean<LoginBean>> {
        return HomeRetrofitManager.service.login(
            "member.androidapi.login",
            mobile,
            pwd
        )
            .compose(SchedulerUtils.ioToMain())
    }

}