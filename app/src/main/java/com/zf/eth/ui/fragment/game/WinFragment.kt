package com.zf.eth.ui.fragment.game

import androidx.recyclerview.widget.LinearLayoutManager
import com.zf.eth.R
import com.zf.eth.base.NotLazyBaseFragment
import com.zf.eth.mvp.bean.WinList
import com.zf.eth.mvp.contract.WinContract
import com.zf.eth.mvp.presenter.WinPresenter
import com.zf.eth.net.exception.ErrorStatus
import com.zf.eth.showToast
import com.zf.eth.ui.adapter.WinAdapter
import kotlinx.android.synthetic.main.fragment_win.*

/**
 * 中奖记录
 */
class WinFragment : NotLazyBaseFragment(), WinContract.View {

    override fun showError(msg: String, errorCode: Int) {
        refreshLayout.setEnableLoadMore(false)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            mLayoutStatusView?.showNoNetwork()
        } else {
            mLayoutStatusView?.showError()
            showToast(msg)
        }
    }

    override fun setWin(bean: List<WinList>) {
        mLayoutStatusView?.showContent()
        refreshLayout.setEnableLoadMore(true)
        data.clear()
        data.addAll(bean)
        adapter.notifyDataSetChanged()
    }

    override fun setLoadMore(bean: List<WinList>) {
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

    override fun showLoading() {
    }

    override fun dismissLoading() {
        refreshLayout.finishRefresh()
        refreshLayout.finishLoadMore()
    }

    companion object {
        fun getInstance(): WinFragment {
            return WinFragment()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_win

    private val adapter by lazy { WinAdapter(context, data) }

    private val data = ArrayList<WinList>()

    private val presenter by lazy { WinPresenter() }

    override fun initView() {
        mLayoutStatusView = multipleStatusView
        presenter.attachView(this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun lazyLoad() {
        refreshLayout.setNoMoreData(false)
        refreshLayout.setEnableLoadMore(false)
        if (data.isEmpty()) {
            mLayoutStatusView?.showLoading()
        }
        presenter.requestWin(1)
    }

    override fun initEvent() {
        refreshLayout.setOnLoadMoreListener {
            presenter.requestWin(null)
        }
    }
}