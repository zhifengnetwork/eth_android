package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.mvp.bean.InvestBean
import com.zf.eth.mvp.contract.InvestContract
import com.zf.eth.mvp.presenter.InvestPresenter
import com.zf.eth.net.exception.ErrorStatus
import com.zf.eth.ui.adapter.InvestAdapter
import kotlinx.android.synthetic.main.activity_invest.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * 投资记录
 */
class InvestActivity : BaseActivity(), InvestContract.View {


    override fun showError(msg: String, errorCode: Int) {
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            mLayoutStatusView?.showNoNetwork()
        } else {
            mLayoutStatusView?.showError()
        }
    }

    override fun setEmpty() {
        mLayoutStatusView?.showEmpty()
    }

    override fun setInvest(bean: List<InvestBean>) {
        mLayoutStatusView?.showContent()
        data.clear()
        data.addAll(bean)
        investAdapter.notifyDataSetChanged()
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    override fun initToolBar() {
        backLayout.setOnClickListener { finish() }
        titleName.text = "投资记录"
    }


    companion object {
        var mType = ""
        val TOUZI = "1"
        val ZHUANBI = "3"
        val TIBI = "4"

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

    private val data = ArrayList<InvestBean>()

    private val investAdapter by lazy { InvestAdapter(this, data) }

    private val presenter by lazy { InvestPresenter() }

    override fun initView() {
        mLayoutStatusView = multipleStatusView
        presenter.attachView(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = investAdapter
    }

    override fun start() {
        mLayoutStatusView?.showLoading()
        presenter.requestInvest(mType)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun initEvent() {
    }


}