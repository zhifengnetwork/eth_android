package com.zf.eth.ui.fragment.c2c

import androidx.recyclerview.widget.LinearLayoutManager
import com.zf.eth.R
import com.zf.eth.base.BaseFragment
import com.zf.eth.mvp.bean.AppealBean
import com.zf.eth.mvp.bean.AppealList
import com.zf.eth.mvp.contract.AppealContract
import com.zf.eth.mvp.presenter.AppealPresenter
import com.zf.eth.ui.adapter.C2cAppealAdapter
import kotlinx.android.synthetic.main.fragment_c2c_appeal.*

class AppealFragment : BaseFragment(), AppealContract.View {
    override fun showError(msg: String, errorCode: Int) {

    }

    override fun getAppeal(bean: AppealBean) {
        mData.clear()
        mData.addAll(bean.list)
        mAdapter.notifyDataSetChanged()
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

    companion object {
        fun getInstance(): AppealFragment {
            return AppealFragment()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_c2c_appeal

    private val presenter by lazy { AppealPresenter() }
    private var mData = ArrayList<AppealList>()
    private val mAdapter by lazy { C2cAppealAdapter(context, mData) }

    override fun initView() {
        presenter.attachView(this)
        appeal_rlv.layoutManager = LinearLayoutManager(context)
        appeal_rlv.adapter = mAdapter
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
        presenter.requestAppeal("1")
    }

}