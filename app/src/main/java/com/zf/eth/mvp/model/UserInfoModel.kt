package com.zf.eth.mvp.model

import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseBean
import com.zf.eth.mvp.bean.UserInfoBean
import com.zf.eth.net.HomeRetrofitManager
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import com.zf.eth.utils.Preference
import io.reactivex.Observable

class UserInfoModel {

    private val userId by Preference(UriConstant.USER_ID, "")

    fun getUserInfo(): Observable<BaseBean<UserInfoBean>> {

        return HomeRetrofitManager.service.getUserInfo(
            "member.androidapi.my_info",
            userId
        ).compose(SchedulerUtils.ioToMain())
    }

    fun setUserLevel(): Observable<BaseBean<Unit>> {
        return RetrofitManager.service.setUserLevel("member.androidapi.lingdaolevel", userId)
            .compose(SchedulerUtils.ioToMain())
    }
}