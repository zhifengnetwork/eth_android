package com.zf.eth.mvp.model

import com.zf.eth.mvp.bean.LoginBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import io.reactivex.Observable

class LoginModel {
    fun login(mobile: String, pwd: String): Observable<LoginBean> {
        return RetrofitManager.service.login("12",
            "entry",
            "ewei_shopv2",
            "mobile",
            "account.login",
            mobile,
            pwd)
            .compose(SchedulerUtils.ioToMain())
    }


}