package com.zf.eth.mvp.model

import com.zf.eth.base.BaseBean
import com.zf.eth.mvp.bean.DownloadBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import io.reactivex.Observable

class DownloadModel{
    fun getDownload():Observable<BaseBean<DownloadBean>>{
        return RetrofitManager.service.getDownload("member.androidapi.download_app")
            .compose(SchedulerUtils.ioToMain())
    }
}