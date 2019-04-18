package com.zf.eth.mvp.model

import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseBean
import com.zf.eth.mvp.bean.ImageViewBean
import com.zf.eth.mvp.bean.PayManageBean
import com.zf.eth.net.RetrofitManager
import com.zf.eth.scheduler.SchedulerUtils
import com.zf.eth.utils.Preference
import io.reactivex.Observable

class PayManageModel {

    private val userId by Preference(UriConstant.USER_ID, "")
    //支付管理信息
    fun getPay(): Observable<BaseBean<PayManageBean>> {
        return RetrofitManager.service.getPayManage("member.androidapi.pay_management", userId)
                .compose(SchedulerUtils.ioToMain())
    }

    //支付管理提交|钱包地址
    fun setPay(adress: String, url: String, zfbfile: String, wxfile: String, bankid: String, bankname: String, bank: String): Observable<BaseBean<Unit>> {
        return RetrofitManager.service.setPayMange("member.androidapi.pay_submit", userId, adress, url, zfbfile, wxfile, bankid, bankname, bank)
                .compose(SchedulerUtils.ioToMain())
    }

    //传图片
    fun requestUpImg(file: String): Observable<BaseBean<ImageViewBean>> {
        return RetrofitManager.service.requestPayImg(
                "member.androidapi.new_file_upload",
                file,
                userId
        ).compose(SchedulerUtils.ioToMain())
    }
}