package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import com.zf.eth.MyApplication.Companion.context
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.mvp.bean.AppealListBean
import com.zf.eth.mvp.bean.DetailList
import com.zf.eth.mvp.contract.AppealListContract
import com.zf.eth.mvp.presenter.AppealListPresenter
import com.zf.eth.showToast
import com.zf.eth.utils.GlideUtils
import kotlinx.android.synthetic.main.activity_c2c_appeal.*

import kotlinx.android.synthetic.main.layout_toolbar.*

class C2cAppealActivity : BaseActivity(), AppealListContract.View {
    override fun showError(msg: String, errorCode: Int) {

    }

    override fun getAppealList(bean: AppealListBean) {
        mData = bean.list
        loadView()
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

    companion object {
        fun actionStart(context: Context?, id: String) {
            val intent = Intent(context, C2cAppealActivity::class.java)
            intent.putExtra("id", id)
            context?.startActivity(intent)
        }
    }

    override fun initToolBar() {
        titleName.text = "申诉详情"
        titleName.textSize = 22f
        titleName.paint.isFakeBoldText = true
        titleBackground.setBackgroundResource(R.drawable.bg1)
        backLayout.setOnClickListener {
            finish()
        }
    }

    override fun layoutId(): Int = R.layout.activity_c2c_appeal

    private val presenter by lazy { AppealListPresenter() }
    private var mData: DetailList? = null
    private var id = ""

    override fun initData() {
        id = intent.getStringExtra("id")
    }

    override fun initView() {
        presenter.attachView(this)

    }

    override fun initEvent() {
        apyImg.setOnClickListener {
            SeeImgActivity.actionStart(this, mData?.files)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun start() {
        presenter.requestAppealList(id)
    }

    /**把数据赋值到页面上*/
    private fun loadView() {
        //申诉问题
        problem.text = mData?.text
        //申诉原因
        reason.text = mData?.textarea
        //申诉订单
        order.text = mData?.id
        //申诉人
        complainant.text = mData?.openid
        //被申诉人
        respondent.text = mData?.openid2
        //ETH数量
        eth_sum.text = mData?.trx
        //CNY数量
        cny_sum.text = mData?.money
        //是否审核
        when (mData?.stuas) {
            "0" -> appeal_type.text = "申诉中"
            "1" -> appeal_type.text = "申诉成功"
            "2" -> appeal_type.text = "申诉失败"
            "3" -> appeal_type.text = "申诉结束"
        }
        //支付凭证
        if (mData?.files != "") {
            img_btn.visibility = View.GONE
            apyImg.visibility = View.VISIBLE
            GlideUtils.loadUrlImage(context, mData?.files, apyImg)
        }


    }
}