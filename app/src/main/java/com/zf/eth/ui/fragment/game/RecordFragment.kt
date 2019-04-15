package com.zf.eth.ui.fragment.game

import androidx.recyclerview.widget.LinearLayoutManager
import com.zf.eth.R
import com.zf.eth.base.BaseFragment
import com.zf.eth.mvp.bean.RecordList
import com.zf.eth.mvp.contract.RecordContract
import com.zf.eth.mvp.presenter.RecordPresenter
import com.zf.eth.net.exception.ErrorStatus
import com.zf.eth.showToast
import com.zf.eth.ui.adapter.RecordAdapter
import kotlinx.android.synthetic.main.fragment_record.*

/**
 * 押注记录
 */
class RecordFragment : BaseFragment(), RecordContract.View {

    override fun setLoadMore(bean: List<RecordList>) {
        data.addAll(bean)
        adapter.notifyDataSetChanged()
    }

    override fun setEmpty() {
        refreshLayout.setEnableLoadMore(false)
        mLayoutStatusView?.showEmpty()
    }

    override fun setLoadComplete() {
        refreshLayout.finishLoadMoreWithNoMoreData()
    }

    override fun loadError(msg: String, errorCode: Int) {
        showToast(msg)
    }

    override fun showError(msg: String, errorCode: Int) {
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            mLayoutStatusView?.showNoNetwork()
        } else {
            mLayoutStatusView?.showError()
            showToast(msg)
        }
    }

    override fun setRecord(bean: List<RecordList>) {
        refreshLayout.setEnableLoadMore(true)
        mLayoutStatusView?.showContent()
        data.clear()
        data.addAll(bean)
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
        refreshLayout.finishRefresh()
        refreshLayout.finishLoadMore()
    }

    companion object {
        fun getInstance(): RecordFragment {
            return RecordFragment()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_record

    private val data = ArrayList<RecordList>()
    private val adapter by lazy { RecordAdapter(context, data) }
    private val presenter by lazy { RecordPresenter() }

    override fun initView() {
        mLayoutStatusView = multipleStatusView
        presenter.attachView(this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    override fun lazyLoad() {
        refreshLayout.setEnableLoadMore(false)
        if (data.isEmpty()) {
            mLayoutStatusView?.showLoading()
        }
        presenter.requestRecord(1)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun initEvent() {
        refreshLayout.setOnLoadMoreListener {
            presenter.requestRecord(null)
        }
    }

}