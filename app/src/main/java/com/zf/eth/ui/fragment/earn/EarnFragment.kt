package com.zf.eth.ui.fragment.earn

import androidx.recyclerview.widget.LinearLayoutManager
import com.zf.eth.R
import com.zf.eth.base.BaseFragment
import com.zf.eth.mvp.bean.EarnBean
import com.zf.eth.mvp.bean.EarnList
import com.zf.eth.mvp.contract.EarnContract
import com.zf.eth.mvp.presenter.EarnPresenter
import com.zf.eth.showToast
import com.zf.eth.ui.adapter.earn.DirectAdapter
import com.zf.eth.ui.adapter.earn.InvestAdapter
import com.zf.eth.ui.adapter.earn.LeaderAdapter
import com.zf.eth.ui.adapter.earn.ManagerAdapter
import kotlinx.android.synthetic.main.fragment_earn.*

class EarnFragment : BaseFragment(), EarnContract.View {

    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
    }

    override fun setEarn(bean: EarnBean) {
        price.text = bean.money
        investData.clear()
        investData.addAll(bean.list)
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    private var mType = 0
    private var mDateType = ""

    companion object {
        fun getInstance(type: Int, dateType: String): EarnFragment {
            val fragment = EarnFragment()
            fragment.mType = type
            fragment.mDateType = dateType
            return fragment
        }
    }

    private val earnPresenter by lazy { EarnPresenter() }

    override fun getLayoutId(): Int = R.layout.fragment_earn

    private val investData = ArrayList<EarnList>()

    private val investAdapter by lazy { InvestAdapter(context, investData) }
    private val directAdapter by lazy { DirectAdapter(context, investData) }
    private val managerAdapter by lazy { ManagerAdapter(context, investData) }
    private val leaderAdapter by lazy { LeaderAdapter(context, investData) }

    override fun initView() {
        earnPresenter.attachView(this)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = when (mType) {
            0 -> investAdapter
            1 -> directAdapter
            2 -> managerAdapter
            3 -> leaderAdapter
            else -> investAdapter
        }

    }

    override fun lazyLoad() {
        when (mType) {
            0 -> earnPresenter.requestEarn("4", mDateType)
            1 -> earnPresenter.requestEarn("1", mDateType)
            2 -> earnPresenter.requestEarn("2", mDateType)
            3 -> earnPresenter.requestEarn("3", mDateType)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        earnPresenter.detachView()
    }

    override fun initEvent() {
    }
}