package com.zf.eth.mvp.model

import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseBean
import com.zf.eth.mvp.bean.BulletinDetailBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import com.zf.eth.utils.Preference
import io.reactivex.Observable

class BulletinDetailModel {
    private val userId by Preference(UriConstant.USER_ID, "")
    fun getBulletinDetail(aid: String): Observable<BaseBean<BulletinDetailBean>> {
        return RetrofitManager.service.getBulletinDetail("member.androidapi.article_detail", userId, aid)
            .compose(SchedulerUtils.ioToMain())
    }
}