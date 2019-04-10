package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.mvp.bean.TeamBean
import com.zf.eth.mvp.contract.TeamContract
import com.zf.eth.mvp.presenter.TeamPresenter
import com.zf.eth.net.exception.ErrorStatus
import com.zf.eth.ui.adapter.TeamAdapter
import kotlinx.android.synthetic.main.activity_team.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * 团队 ->下级明细
 */
class TeamActivity : BaseActivity(), TeamContract.View {

    override fun freshEmpty() {
        mLayoutStatusView?.showEmpty()
    }

    override fun showError(msg: String, errorCode: Int) {
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            mLayoutStatusView?.showNoNetwork()
        } else {
            mLayoutStatusView?.showError()
        }
    }

    override fun setTeam(bean: List<TeamBean>) {
        mLayoutStatusView?.showContent()
        data.clear()
        data.addAll(bean)
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    companion object {
        fun actionStart(context: Context?) {
            context?.startActivity(Intent(context, TeamActivity::class.java))
        }
    }

    override fun initToolBar() {
        backLayout.setOnClickListener { finish() }
        titleName.text = "下级明细"
    }

    override fun layoutId(): Int = R.layout.activity_team

    override fun initData() {
    }

    private val data = ArrayList<TeamBean>()

    private val adapter by lazy { TeamAdapter(this, data) }

    private val presenter by lazy { TeamPresenter() }

    override fun initView() {
        mLayoutStatusView = multipleStatusView
        presenter.attachView(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun initEvent() {
    }

    override fun start() {
        mLayoutStatusView?.showLoading()
        presenter.requestTeam()
    }
}