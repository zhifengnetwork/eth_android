package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import android.util.Log
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.mvp.bean.DownloadBean
import com.zf.eth.mvp.contract.DownloadContract
import com.zf.eth.mvp.presenter.DownloadPresenter
import kotlinx.android.synthetic.main.layout_toolbar.*

class DownloadActivity:BaseActivity(),DownloadContract.View{
    override fun showError(msg: String, errorCode: Int) {

    }

    override fun getDownload(bean: DownloadBean) {
        Log.e("检测","下载接口接收数据成功")
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

    companion object {
        fun actionStart(context: Context?){
            context?.startActivity(Intent(context,DownloadActivity::class.java))
        }
    }
    override fun initToolBar() {
        titleName.text="下载"
    }
    override fun layoutId(): Int = R.layout.activity_download

    private val presenter by lazy { DownloadPresenter() }


    override fun initData() {

    }

    override fun initView() {
        presenter.attachView(this)
    }

    override fun initEvent() {

    }

    override fun start() {
       presenter.requestDownload()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

}