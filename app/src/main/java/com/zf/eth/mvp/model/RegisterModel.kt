package com.zf.eth.mvp.model

import com.zf.eth.base.BaseBean
import com.zf.eth.mvp.bean.RegisterBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import io.reactivex.Observable

class RegisterModel {

    //获取验证码
    fun requestVerifyCode(mobile: String, temp: String): Observable<BaseBean<Unit>> {
        return RetrofitManager.service.requestVerifyCode(
            "member.androidapi.verifycode",
            "0",
            mobile,
            temp
        )
            .compose(SchedulerUtils.ioToMain())
    }

    fun requestRegister(
        type: String,
        mobile: String,
        code: String,
        pwd: String,
        agentId: String
    ): Observable<BaseBean<RegisterBean>> {
        return RetrofitManager.service.requestRegister(
            "member.androidapi.reg_updpwd",
            type,
            mobile,
            code,
            pwd,
            agentId
        )
            .compose(SchedulerUtils.ioToMain())
    }

}