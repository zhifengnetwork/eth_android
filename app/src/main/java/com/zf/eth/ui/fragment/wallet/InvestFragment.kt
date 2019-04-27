package com.zf.eth.ui.fragment.wallet

import androidx.recyclerview.widget.LinearLayoutManager
import com.zf.eth.R
import com.zf.eth.base.NotLazyBaseFragment
import com.zf.eth.mvp.bean.InvestList
import com.zf.eth.mvp.contract.InvestContract
import com.zf.eth.mvp.presenter.InvestPresenter
import com.zf.eth.net.exception.ErrorStatus
import com.zf.eth.showToast
import com.zf.eth.ui.adapter.*
import kotlinx.android.synthetic.main.fragment_invest.*
import kotlinx.android.synthetic.main.layout_state_empty_invest.*

/**
 * 投资记录 提币记录 转币记录 C2c记录
 *
 */
class InvestFragment : NotLazyBaseFragment(), InvestContract.View {

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
        emptyText.text = when (mType) {
            TOUZI -> "没有投资记录"
            ZHUANBI -> "没有转币记录"
            TIBI -> "没有提币记录"
            C2C -> "没有C2C记录"
            else -> "没有记录"
        }
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

    private var mType = ""

    companion object {
        const val TOUZI = "1"
        const val ZHUANBI = "3"
        const val TIBI = "4"
        const val C2C = "5"
        const val TOTAL = "6"
        fun newInstance(type: String): InvestFragment {
            val fragment = InvestFragment()
            fragment.mType = type
            return fragment
        }
    }

    private val data = ArrayList<InvestList>()

    private val presenter by lazy { InvestPresenter() }

    //投资记录
    private val investAdapter by lazy { InvestAdapter(context, data) }
    //总记录
    private val totalAdapter by lazy { TotalAdapter(context, data) }
    //提现
    private val withDrawAdapter by lazy { WithDrawAdapter(context, data) }
    //C2C
    private val c2cAdapter by lazy { C2CRecordAdapter(context, data) }
    //转币
    private val transferAdapter by lazy { TransferAdapter(context, data) }

    override fun getLayoutId(): Int = R.layout.fragment_invest

    override fun initView() {
        mLayoutStatusView = multipleStatusView
        presenter.attachView(this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter =
            when (mType) {
                C2C -> c2cAdapter
                ZHUANBI -> transferAdapter
                TIBI -> withDrawAdapter
                TOTAL -> totalAdapter
                else -> investAdapter
            }
    }

    override fun lazyLoad() {
        refreshLayout.setNoMoreData(false)
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