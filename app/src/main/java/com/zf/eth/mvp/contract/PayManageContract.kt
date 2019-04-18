package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter
import com.zf.eth.mvp.bean.PayManageBean

interface PayManageContract {
    interface View : IBaseView {
        fun showError(msg: String, errorCode: Int)
        //支付管理信息
        fun getPay(bean: PayManageBean)

        //修改支付管理信息和钱包地址
        fun editPaySuccess()

        fun setWechatImg(url: String)

        fun setAlipayImg(url: String)

        fun setAddressImg(url: String)
    }

    interface Presenter : IPresenter<View> {
        //支付管理信息
        fun requestPay()

        //修改支付管理信息和钱包地址
        fun requestEditPayManege(adress: String, url: String, zfbfile: String, wxfile: String, bankid: String, bankname: String, bank: String)

        fun requestUpImg(file: String, type: String?)
    }
}