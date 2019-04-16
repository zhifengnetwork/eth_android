package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.mvp.bean.InvestList
import com.zf.eth.mvp.contract.InvestContract
import com.zf.eth.mvp.presenter.InvestPresenter
import com.zf.eth.net.exception.ErrorStatus
import com.zf.eth.showToast
import com.zf.eth.ui.adapter.C2CRecordAdapter
import com.zf.eth.ui.adapter.InvestAdapter
import com.zf.eth.ui.adapter.TransferAdapter
import kotlinx.android.synthetic.main.activity_invest.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * 投资记录
 */
class InvestActivity : BaseActivity(), InvestContract.View {

    override fun loadError(msg: String, errorCode: Int) {
        showToast(msg)
    }

    override fun setLoadComplete() {
        refreshLayout.finishLoadMoreWithNoMoreData()
    }


    override fun showError(msg: String, errorCode: Int) {
        refreshLayout.setEnableLoadMore(false)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            mLayoutStatusView?.showNoNetwork()
        } else {
            mLayoutStatusView?.showError()
            showToast(msg)
        }
    }

    override fun setEmpty() {
        refreshLayout.setEnableLoadMore(false)
        mLayoutStatusView?.showEmpty()
    }

    override fun setLoadMore(bean: List<InvestList>) {
        data.addAll(bean)
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun setInvest(bean: List<InvestList>) {
        refreshLayout.setEnableLoadMore(true)
        mLayoutStatusView?.showContent()
        data.clear()
        data.addAll(bean)
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
        refreshLayout.finishRefresh()
        refreshLayout.finishLoadMore()
    }

    override fun initToolBar() {
        backLayout.setOnClickListener { finish() }
        if (mType == C2C) {
            titleName.text = "C2C交易记录"
        } else {
            titleName.text = "投资记录"
        }
    }

    var mType = ""

    companion object {
        const val TOUZI = "1"
        const val ZHUANBI = "3"
        const val TIBI = "4"
        const val C2C = "5"

        fun actionStart(context: Context?, type: String) {
            val intent = Intent(context, InvestActivity::class.java)
            intent.putExtra("type", type)
            context?.startActivity(intent)
        }
    }

    override fun layoutId(): Int = R.layout.activity_invest

    override fun initData() {
        mType = intent.getStringExtra("type")
    }

    private val data = ArrayList<InvestList>()

    private val investAdapter by lazy { InvestAdapter(this, data) }
    private val c2cAdapter by lazy { C2CRecordAdapter(this, data) }
    private val transferAdapter by lazy { TransferAdapter(this, data) }

    private val presenter by lazy { InvestPresenter() }

    override fun initView() {
        mLayoutStatusView = multipleStatusView
        presenter.attachView(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter =
            if (mType == C2C) c2cAdapter else if (mType == ZHUANBI) transferAdapter else investAdapter

    }

    override fun start() {
        refreshLayout.setEnableLoadMore(false)
        if (data.isEmpty()) {
            mLayoutStatusView?.showLoading()
        }
        presenter.requestInvest(mType, 1)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun initEvent() {
        refreshLayout.setOnLoadMoreListener {
            presenter.requestInvest(mType, null)
        }
    }


}