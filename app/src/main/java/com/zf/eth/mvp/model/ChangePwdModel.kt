package com.zf.eth.mvp.model

import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import com.zf.eth.utils.Preference
import io.reactivex.Observable

class ChangePwdModel {

    private val userId by Preference(UriConstant.USER_ID, "")

    //获取验证码
    fun requestVerifyCode(mobile: String, temp: String): Observable<BaseBean<Unit>> {
        return RetrofitManager.service.requestVerifyCode(
                "member.androidapi.verifycode",
                userId,
                "0",
                mobile,
                temp
        )
                .compose(SchedulerUtils.ioToMain())
    }

    //修改密码
    fun requestChangePwd(mobile: String, code: String, pwd: String): Observable<BaseBean<Unit>> {
        return RetrofitManager.service.requestChangePwd(
                "member.androidapi.cahngepwd",
                userId,
                mobile,
                code,
                pwd
        ).compose(SchedulerUtils.ioToMain())
    }

}