package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.mvp.bean.TeamList
import com.zf.eth.mvp.contract.TeamContract
import com.zf.eth.mvp.presenter.TeamPresenter
import com.zf.eth.net.exception.ErrorStatus
import com.zf.eth.showToast
import com.zf.eth.ui.adapter.TeamAdapter
import kotlinx.android.synthetic.main.activity_team.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * 团队 ->下级明细
 */
class TeamActivity : BaseActivity(), TeamContract.View {

    override fun setLoadMore(bean: List<TeamList>) {
        initTeam(bean)
        adapter.notifyDataSetChanged()
    }

    override fun setLoadComplete() {
        refreshLayout.finishLoadMoreWithNoMoreData()
    }

    override fun loadMoreError(msg: String, errorCode: Int) {
        showToast(msg)
    }

    override fun freshEmpty() {
        refreshLayout.setEnableLoadMore(false)
        mLayoutStatusView?.showEmpty()
    }

    override fun showError(msg: String, errorCode: Int) {
        refreshLayout.setEnableLoadMore(false)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            mLayoutStatusView?.showNoNetwork()
        } else {
            mLayoutStatusView?.showError()
        }
    }

    override fun setTeam(bean: List<TeamList>) {
        refreshLayout.setEnableLoadMore(true)
        mLayoutStatusView?.showContent()
        data.clear()
        initTeam(bean)
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
        refreshLayout.finishRefresh()
        refreshLayout.finishLoadMore()
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

    private val data = ArrayList<TeamList>()

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

        refreshLayout.setOnLoadMoreListener {
            presenter.requestTeam(null)
        }
    }

    override fun start() {
        refreshLayout.setEnableLoadMore(false)
        if (data.isEmpty()) {
            mLayoutStatusView?.showLoading()
        }
        presenter.requestTeam(1)
    }

    private fun initTeam(a: List<TeamList>) {

        val mTon = ArrayList<TeamList>()
        val mUp = ArrayList<TeamList>()

        for (i in 0 until a.size) {
            //type=1 为直推
            if (a[i].type == 1) {
                mTon.add(a[i])
            } else {
                mUp.add(a[i])
            }
        }
        mTon.sortBy {
            it.createtime
        }
        mUp.sortBy {
            it.createtime
        }
        mTon.reverse()
        mUp.reverse()
        data.addAll(mTon)
        data.addAll(mUp)

    }
}