package com.zf.eth.ui.fragment

import com.zf.eth.R
import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseFragment
import com.zf.eth.mvp.bean.HomeSetBean
import com.zf.eth.mvp.contract.HomeContract
import com.zf.eth.mvp.presenter.HomePresenter
import com.zf.eth.showToast
import com.zf.eth.ui.activity.*
import com.zf.eth.utils.GlideImageLoader
import com.zf.eth.utils.HtmlLabel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_home_function.*

class HomeFragment : BaseFragment(), HomeContract.View {


    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
    }

    //首页信息
    override fun setHome(bean: HomeSetBean) {
        //投资总额
        grossAsset.text = bean.data.touzimoney
        //总收益
        totalRevenue.text = bean.data.shouyimoneysum
        //今日收益
        todayIncome.text = bean.data.shouyimoney
        //钱包余额
        balance.text = bean.data.money
        //团队
        team.text = bean.data.xiaji

        /** 轮播图*/
        val images = ArrayList<String>()
        for (banner in bean.slide) {
            images.add(UriConstant.BASE_IMG_URL + banner.thumb)
        }
        banner.setImageLoader(GlideImageLoader())
        banner.setImages(images)
        banner.start()

        /** 公告*/
        val noticeData = ArrayList<String>()
        for (notice in bean.notice) {
            noticeData.add(HtmlLabel.stringHtml(notice.detail))
        }
        bannerTextView.setDatas(noticeData)
        bannerTextView.startViewAnimator()

    }


    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    companion object {
        fun getInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_home

    private val bannerPresenter by lazy { HomePresenter() }

    override fun initView() {
        bannerPresenter.attachView(this)
    }

    override fun onResume() {
        super.onResume()
        bannerTextView.startViewAnimator()
    }

    override fun onStop() {
        super.onStop()
        bannerTextView.stopViewAnimator()
    }

    override fun onDestroy() {
        super.onDestroy()
        bannerPresenter.detachView()
    }

    override fun lazyLoad() {
        bannerPresenter.requestHome()
    }

    override fun initEvent() {

        //总收益
        totalEarnLayout.setOnClickListener {
            EarnActivity.actionStart(context)
        }

        //今日收益
        todayEarnLayout.setOnClickListener {
            EarnActivity.actionStart(context)
        }

        //团队
        teamLayout.setOnClickListener {
            TeamActivity.actionStart(context)
        }

        //激活账号（投资购买）
        buyLayout.setOnClickListener {
            BuyActivity.actionStart(context)
        }

        //投资记录
        investLayout.setOnClickListener {
            InvestActivity.actionStart(context, InvestActivity.TOUZI)
        }

        //钱包余额
        walletLayout.setOnClickListener {
            WalletActivity.actionStart(context)
        }

        share.setOnClickListener {
            showToast("分享")
        }
    }
}