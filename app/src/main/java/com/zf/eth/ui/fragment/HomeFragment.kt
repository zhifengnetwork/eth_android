package com.zf.eth.ui.fragment

import com.zf.eth.R
import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseFragment
import com.zf.eth.mvp.bean.BannerBean
import com.zf.eth.mvp.bean.NoticeBean
import com.zf.eth.mvp.contract.HomeContract
import com.zf.eth.mvp.presenter.HomePresenter
import com.zf.eth.showToast
import com.zf.eth.ui.activity.BuyActivity
import com.zf.eth.ui.activity.InvestActivity
import com.zf.eth.ui.activity.TeamActivity
import com.zf.eth.ui.activity.WalletActivity
import com.zf.eth.utils.GlideImageLoader
import com.zf.eth.utils.HtmlLabel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_home_function.*

class HomeFragment : BaseFragment(), HomeContract.View {


    override fun showError(msg: String, errorCode: Int) {

    }

    //轮播图
    override fun setBanner(bean: List<BannerBean>) {
        val images = ArrayList<String>()
        for (banner in bean) {
            images.add(UriConstant.BASE_IMG_URL + banner.thumb)
        }
        banner.setImageLoader(GlideImageLoader())
        banner.setImages(images)
        banner.start()
    }

    //公告
    override fun setNotice(bean: List<NoticeBean>) {
        val noticeData = ArrayList<String>()
        for (notice in bean) {
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
        bannerPresenter.requestBanner()
        bannerPresenter.requestNotice()
    }

    override fun initEvent() {

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
            InvestActivity.actionStart(context)
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