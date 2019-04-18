package com.zf.eth.mvp.model

import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseBean
import com.zf.eth.mvp.bean.BuyBean
import com.zf.eth.mvp.bean.ImageViewBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import com.zf.eth.utils.Preference
import io.reactivex.Observable

class BuyModel {

    private val userId by Preference(UriConstant.USER_ID, "")

    fun requestBuyInfo(): Observable<BaseBean<BuyBean>> {
        return RetrofitManager.service.requestBuyInfo(
                "member.androidapi.payment",
                userId
        ).compose(SchedulerUtils.ioToMain())
    }

    fun requestPayImg(file: String): Observable<BaseBean<ImageViewBean>> {
        return RetrofitManager.service.requestPayImg(
                "member.androidapi.new_file_upload",
                file,
                userId
        ).compose(SchedulerUtils.ioToMain())
    }

    fun requestConfirmPay(money: String, url: String): Observable<BaseBean<Unit>> {
        return RetrofitManager.service.requestConfirmPay(
                "member.androidapi.wechat_complete1",
                userId,
                money,
                url
        ).compose(SchedulerUtils.ioToMain())
    }

}