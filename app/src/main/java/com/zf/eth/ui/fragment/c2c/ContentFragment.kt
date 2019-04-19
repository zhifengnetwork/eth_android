package com.zf.eth.ui.fragment.c2c

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.zf.eth.R
import com.zf.eth.base.BaseFragment
import com.zf.eth.mvp.bean.C2cBean
import com.zf.eth.mvp.bean.C2cList
import com.zf.eth.mvp.bean.TeamList
import com.zf.eth.mvp.contract.C2cContract
import com.zf.eth.mvp.presenter.C2cPresenter
import com.zf.eth.net.exception.ErrorStatus
import com.zf.eth.showToast
import com.zf.eth.ui.adapter.C2cContentAdapter
import kotlinx.android.synthetic.main.layout_c2c_content.*

class ContentFragment : BaseFragment(), C2cContract.View {

    //当第一页数据为空时
    override fun freshEmpty() {
        mLayoutStatusView?.showEmpty()
        refreshLayout.setEnableLoadMore(false)
//        null_ly.visibility = View.VISIBLE
//        refreshLayout.visibility = View.GONE
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
        if (errorCode==ErrorStatus.NETWORK_ERROR){
            mLayoutStatusView?.showNoNetwork()
        }else{
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


    override fun getLayoutId(): Int = R.layout.layout_c2c_content

    override fun initView() {
        Log.e("检测", "C2c界面打开")
        Log.e("检测", "Type值为" + mType)

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
            if (it.type == "1") {
                c2cPresenter.requesC2cSellout(it.id, "0")
            } else {
                c2cPresenter.requesC2cSellout(it.id, "1")
            }
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
        c2cPresenter.requesC2c(1, if (mType== BUY) "1" else "0")
    }

    override fun onDestroy() {
        super.onDestroy()
        c2cPresenter.detachView()
    }





}