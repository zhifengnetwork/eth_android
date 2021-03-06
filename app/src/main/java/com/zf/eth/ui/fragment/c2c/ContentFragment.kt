package com.zf.eth.ui.fragment.c2c


import androidx.recyclerview.widget.LinearLayoutManager
import com.zf.eth.R
import com.zf.eth.base.BaseFragment
import com.zf.eth.mvp.bean.C2cBean
import com.zf.eth.mvp.bean.C2cList
import com.zf.eth.mvp.bean.PayManageBean
import com.zf.eth.mvp.contract.C2cContract
import com.zf.eth.mvp.contract.PayManageContract
import com.zf.eth.mvp.presenter.C2cPresenter
import com.zf.eth.net.exception.ErrorStatus
import com.zf.eth.showToast
import com.zf.eth.ui.activity.C2cDetailActivity
import com.zf.eth.ui.activity.PaymentActivity
import com.zf.eth.ui.adapter.C2cContentAdapter
import kotlinx.android.synthetic.main.layout_c2c_content.*
import kotlinx.android.synthetic.main.pop_notice.*

class ContentFragment : BaseFragment(), C2cContract.View {
    override fun getPay(bean: PayManageBean) {
        if (bean.bankid != "" || bean.zfbfile != "" || bean.wxfile != "") {
            if (mC2cList.type == "1") {
                c2cPresenter.requesC2cSellout(mC2cList.id, "0")
            } else {
                c2cPresenter.requesC2cSellout(mC2cList.id, "1")
            }
        }else{
            showToast("请上传支付信息")
            PaymentActivity.actionStart(context)
        }
    }

    //买入卖出错误时
    override fun setBuyError(msg: String) {
        showToast(msg)

    }

    //当第一页数据为空时
    override fun freshEmpty() {
        mLayoutStatusView?.showEmpty()
        refreshLayout.setEnableLoadMore(false)
    }

    //不是第一页获得数据
    override fun setLoadMore(bean: C2cBean) {
        c2cList.addAll(bean.list)
        adapter.notifyDataSetChanged()
    }

    //实际获得数据小于一页最大数据时
    override fun setLoadComplete() {
        refreshLayout.finishLoadMoreWithNoMoreData()
    }

    //下拉加载错误
    override fun loadMoreError(msg: String, errorCode: Int) {
        showToast(msg)
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

    //第一页获得数据
    override fun setC2c(bean: C2cBean) {
        mLayoutStatusView?.showContent()
        refreshLayout.setEnableLoadMore(true)
        c2cList.clear()
        c2cList.addAll(bean.list)
        adapter.notifyDataSetChanged()
    }

    override fun setSelloutSuccess(msg: String) {
        showToast(msg)
        //成功时跳转页面
        C2cDetailActivity.actionStart(context, "BUY")
        lazyLoad()
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {
        refreshLayout.finishRefresh()
        refreshLayout.finishLoadMore()
    }

    private var mType = ""

    //在真正的开发中，每个界面的ID可能是不同的，所以这里会接收一个ID
    companion object {
        const val BUY = "BUY"
        const val SELL = "SELL"
        fun buildFragment(type: String): ContentFragment {
            val fragment = ContentFragment()
            fragment.mType = type
            return fragment
        }
    }

    /**网络请求*/
    private val c2cPresenter by lazy { C2cPresenter() }

    private val c2cList = ArrayList<C2cList>()

    private val adapter by lazy { C2cContentAdapter(context, c2cList) }

    private lateinit var mC2cList: C2cList

    override fun getLayoutId(): Int = R.layout.layout_c2c_content

    override fun initView() {

        mLayoutStatusView = multipleStatusView
        c2cPresenter.attachView(this)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

    }


    override fun initEvent() {
        /**
         * 点击卖出买入按钮 网络请求 (买入和卖出相反)
         * */
        adapter.mClickListener = {
            c2cPresenter.requestPay()
            mC2cList = it

        }

        /**上拉加载*/
        refreshLayout.setOnLoadMoreListener {
            if (mType == BUY) {
                c2cPresenter.requesC2c(null, "1")
            } else {
                c2cPresenter.requesC2c(null, "0")
            }
        }
        /**下拉刷新*/
        refreshLayout.setOnRefreshListener {
            lazyLoad()

        }
    }

    override fun lazyLoad() {
        refreshLayout.setEnableLoadMore(false)
        c2cPresenter.requesC2c(1, if (mType == BUY) "1" else "0")
    }

    override fun onDestroy() {
        super.onDestroy()
        c2cPresenter.detachView()
    }


}