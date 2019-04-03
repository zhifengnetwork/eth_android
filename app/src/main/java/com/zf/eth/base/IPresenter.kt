package com.zf.eth.base

interface IPresenter<in V: IBaseView> {

    fun attachView(mRootView: V)

    fun detachView()

}