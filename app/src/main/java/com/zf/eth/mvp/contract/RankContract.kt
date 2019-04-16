package com.zf.eth.mvp.contract

import com.zf.eth.base.IBaseView
import com.zf.eth.base.IPresenter
import com.zf.eth.mvp.bean.RankBean

interface RankContract {

    interface View : IBaseView {

        fun showError(msg: String, errorCode: Int)

        fun setRank(bean: RankBean)

    }

    interface Presenter : IPresenter<View> {

        fun requestRank()

    }

}