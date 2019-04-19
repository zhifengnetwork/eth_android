package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.mvp.bean.InviteBean
import com.zf.eth.mvp.contract.InviteContract
import com.zf.eth.mvp.presenter.InvitePresenter
import com.zf.eth.showToast
import com.zf.eth.utils.GlideUtils
import kotlinx.android.synthetic.main.activity_invite.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * 我的邀请
 */
class InviteActivity : BaseActivity(), InviteContract.View {
    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
    }

    override fun getInvite(bean: InviteBean) {
        GlideUtils.loadUrlImage(this, bean.img, image)
    }

    override fun showLoading() {
        showLoadingDialog()
    }

    override fun dismissLoading() {
        dismissLoadingDialog()
    }

    companion object {
        fun actionStart(context: Context?) {
            context?.startActivity(Intent(context, InviteActivity::class.java))
        }
    }

    override fun initToolBar() {
        backLayout.setOnClickListener { finish() }
        titleName.text = "我的邀请"
    }

    private val presenter by lazy { InvitePresenter() }

    override fun layoutId(): Int = R.layout.activity_invite

    override fun initData() {
    }

    override fun initView() {
        presenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun initEvent() {
    }

    override fun start() {
        presenter.requestInvite()
    }
}