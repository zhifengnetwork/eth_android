package com.zf.eth.ui.fragment.c2c

import androidx.recyclerview.widget.LinearLayoutManager
import com.zf.eth.R
import com.zf.eth.base.BaseFragment
import com.zf.eth.mvp.bean.MyOrderBean
import com.zf.eth.mvp.contract.MyOrderContract
import com.zf.eth.mvp.presenter.MyOrderPresenter
import com.zf.eth.ui.adapter.OrderContentAdapter
import kotlinx.android.synthetic.main.fragment_c2c_order_content.*

class OrderContentFragment:BaseFragment(),MyOrderContract.View{
    override fun showError(msg: String, errorCode: Int) {

    }

    override fun getMyOrder(bean: List<MyOrderBean>) {
        mData.clear()
        mData.addAll(bean)
        mAdapter.notifyDataSetChanged()
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

    private var mType=""
    companion object {
        const val One = "0"
        const val Two = "1"
        const val Three = "2"
        const val Four = "3"
        fun getInstance(type:String):OrderContentFragment {
            val fragment = OrderContentFragment()
            fragment.mType=type
            return fragment
        }
    }
    override fun getLayoutId(): Int = R.layout.fragment_c2c_order_content

    private var mData=ArrayList<MyOrderBean>()

    private val mAdapter by lazy { OrderContentAdapter(context,mData) }

    private val presenter by lazy { MyOrderPresenter() }
    override fun initView() {
        presenter.attachView(this)
        order_rv.layoutManager=LinearLayoutManager(context)
        order_rv.adapter=mAdapter
    }

    override fun lazyLoad() {

    }

    override fun initEvent() {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
    override fun onStart() {
        super.onStart()
        presenter.requestMyOrder(mType)


    }

}