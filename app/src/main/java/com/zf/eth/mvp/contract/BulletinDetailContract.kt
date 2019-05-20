package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter
import com.zf.eth.mvp.bean.BulletinDetail
import com.zf.eth.mvp.bean.BulletinDetailBean

interface BulletinDetailContract {
    interface View : IBaseView {
        fun showError(msg: String, errorCode: Int)
        fun getBulletinDetail(bean: BulletinDetailBean)
    }

    interface Presenter : IPresenter<View> {
        fun requseBulletinDetail(aid: String)
    }
}