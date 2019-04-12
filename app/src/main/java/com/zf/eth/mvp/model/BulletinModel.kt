package com.zf.eth.mvp.model

import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseBean
import com.zf.eth.mvp.bean.BulletinBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import com.zf.eth.utils.Preference
import io.reactivex.Observable

class BulletinModel{
    private val userId by Preference(UriConstant.USER_ID, "")
    fun getBulletin(page:String,cateid:String):Observable<BaseBean<BulletinBean>>{
          return RetrofitManager.service.getBulletin("member.androidapi.article_getlist",userId,page,cateid)
              .compose(SchedulerUtils.ioToMain())
    }
}