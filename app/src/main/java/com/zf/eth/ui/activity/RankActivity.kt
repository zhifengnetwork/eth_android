package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.base.BaseFragmentAdapter
import com.zf.eth.mvp.bean.RankBean
import com.zf.eth.mvp.contract.RankContract
import com.zf.eth.mvp.presenter.RankPresenter
import com.zf.eth.ui.fragment.RankFragment
import com.zf.eth.ui.fragment.RankWinFragment
import kotlinx.android.synthetic.main.activity_rank.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class RankActivity : BaseActivity(), RankContract.View {

    override fun showError(msg: String, errorCode: Int) {
    }


    override fun setRank(bean: RankBean) {

        total.text = bean.total

        val titles = arrayListOf("今日投资排名", "昨日投资数据", "中奖名单")
        val fgms = arrayListOf(
            RankFragment.getInstance(RankFragment.TODAY, bean),
            RankFragment.getInstance(RankFragment.YESTERDAY, bean),
            RankWinFragment.getInstance(bean)
        )
        val adapter = BaseFragmentAdapter(supportFragmentManager, fgms, titles)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 3
        tabLayout.setViewPager(viewPager)

    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    companion object {
        fun actionStart(context: Context?) {
            context?.startActivity(Intent(context, RankActivity::class.java))
        }
    }

    override fun initToolBar() {
        backLayout.setOnClickListener { finish() }
        titleName.text = "投资排行"
    }

    override fun layoutId(): Int = R.layout.activity_rank

    private val presenter by lazy { RankPresenter() }

    override fun initData() {
    }

    override fun initView() {
        presenter.attachView(this)
    }

    override fun initEvent() {
    }

    override fun start() {
        presenter.requestRank()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

}