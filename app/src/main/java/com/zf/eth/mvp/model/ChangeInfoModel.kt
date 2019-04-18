package com.zf.eth.mvp.model

import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseBean
import com.zf.eth.mvp.bean.ImageViewBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import com.zf.eth.utils.Preference
import io.reactivex.Observable

class ChangeInfoModel {

    private val userId by Preference(UriConstant.USER_ID, "")

    fun requestUpHead(file: String): Observable<BaseBean<ImageViewBean>> {
        return RetrofitManager.service.requestChangeHead(
            "member.androidapi.new_file_upload",
            userId,
            file
        )
            .compose(SchedulerUtils.ioToMain())
    }

    fun requestChangeInfo(nickname: String, avatar: String): Observable<BaseBean<Unit>> {
        return RetrofitManager.service.requestChangeInfo(
            "member.androidapi.face",
            userId,
            nickname,
            avatar
        )
            .compose(SchedulerUtils.ioToMain())
    }


}