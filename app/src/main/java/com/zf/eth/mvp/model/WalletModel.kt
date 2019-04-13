package com.zf.eth.mvp.model

import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseBean
import com.zf.eth.mvp.bean.ChargeBean
import com.zf.eth.mvp.bean.WalletBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import com.zf.eth.utils.Preference
import io.reactivex.Observable

class WalletModel {

    private val userId by Preference(UriConstant.USER_ID, "")

    fun requestWallet(): Observable<BaseBean<WalletBean>> {
        return RetrofitManager.service.getWallet(
            "member.androidapi.my_wallet",
            userId
        )
            .compose(SchedulerUtils.ioToMain())
    }


    fun requestCharge(): Observable<BaseBean<ChargeBean>> {
        return RetrofitManager.service.requestCharge(
            "member.androidapi.get_sxf",
            userId
        )
            .compose(SchedulerUtils.ioToMain())
    }

}