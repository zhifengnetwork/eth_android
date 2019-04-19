package com.zf.eth.mvp.model

import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseBean
import com.zf.eth.mvp.bean.ImageViewBean
import com.zf.eth.mvp.bean.OrderDetailBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import com.zf.eth.utils.Preference
import io.reactivex.Observable

class ConfirmOrderModel {
    private val userId by Preference(UriConstant.USER_ID, "")
    fun setConfirmOrder(id: String, file: String): Observable<BaseBean<Unit>> {
        return RetrofitManager.service.setConfirmOrder(
                "member.androidapi.selloutyes",
                userId,
                id,
                file
        ).compose(SchedulerUtils.ioToMain())
    }

    fun requestPayImg(file: String): Observable<BaseBean<ImageViewBean>> {
        return RetrofitManager.service.requestPayImg(
                "member.androidapi.new_file_upload",
                file,
                userId
        ).compose(SchedulerUtils.ioToMain())
    }

    fun requestOrderDetail(id: String): Observable<BaseBean<OrderDetailBean>>{
        return RetrofitManager.service.getOrderDetail(
            "member.androidapi.guamaiedit",
            userId,
            id
        ).compose(SchedulerUtils.ioToMain())
    }
}