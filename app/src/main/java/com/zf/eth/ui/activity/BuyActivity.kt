package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import android.view.View
import com.yanzhenjie.album.Album
import com.zf.eth.R
import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseActivity
import com.zf.eth.mvp.bean.BuyBean
import com.zf.eth.mvp.contract.BuyContract
import com.zf.eth.mvp.presenter.BuyPresenter
import com.zf.eth.showToast
import com.zf.eth.utils.Base64Utils
import com.zf.eth.utils.GlideUtils
import com.zf.eth.utils.LogUtils
import com.zf.eth.utils.PriceInputFilter
import kotlinx.android.synthetic.main.activity_buy.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import java.math.BigDecimal

/**
 * 账户激活 投资购买
 */
class BuyActivity : BaseActivity(), BuyContract.View {

    private var mUrl = ""

    //确定付款
    override fun setConfirmPay() {
        showToast("购买成功")
        finish()
    }

    //支付凭证
    override fun setPayImg(url: String) {
        mUrl = url
        GlideUtils.loadUrlImage(this, mUrl, payImg)
        showToast("上传凭证成功")
    }

    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
    }

    override fun setBuyInfo(bean: BuyBean) {
        currentPrice.text = bean.list.credit1
        typeTxt.text = if (bean.list.type == "0") "激活投资" else "追加投资"
        upLimit.text = bean.list.bibi
        address.text = bean.list.add
        mostPrice.text = (BigDecimal(bean.list.bibi).subtract(BigDecimal(bean.list.credit1))).toString()


        GlideUtils.loadUrlImage(this, UriConstant.BASE_IMG_URL + bean.list.weixinfile, qrCode)
    }

    override fun showLoading() {
        showLoadingDialog()
    }

    override fun dismissLoading() {
        dismissLoadingDialog()
    }

    companion object {
        fun actionStart(context: Context?) {
            context?.startActivity(Intent(context, BuyActivity::class.java))
        }
    }

    override fun initToolBar() {
        backLayout.setOnClickListener { finish() }
        titleName.text = "投资购买"
    }

    override fun layoutId(): Int = R.layout.activity_buy

    private val presenter by lazy { BuyPresenter() }

    override fun initData() {
    }

    override fun initView() {
        presenter.attachView(this)
        price.filters = arrayOf(PriceInputFilter())
    }

    override fun initEvent() {

        //确定购买
        confirmPay.setOnClickListener {
            if (mUrl.isEmpty()) {
                showToast("请上传支付凭证")
            } else {
                presenter.requestConfirmPay(price.text.toString(), mUrl)
            }
        }

        //上传支付凭证
        payImg.setOnClickListener {
            Album.image(this)
                    .multipleChoice()
                    .camera(true)
                    .columnCount(3)
                    .selectCount(1)
                    .onResult {
                        val base64 = Base64Utils.bitmapToString(it[0].path)
                        presenter.requestPayImg(base64)
                    }
                    .start()
        }

        buy.setOnClickListener {
            detailLayout.visibility = View.VISIBLE
            buy.visibility = View.GONE
        }
    }

    override fun start() {
        presenter.requestBuyInfo()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

}