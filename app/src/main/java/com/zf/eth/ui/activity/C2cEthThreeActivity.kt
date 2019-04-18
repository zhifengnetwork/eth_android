package com.zf.eth.ui.activity

import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.yanzhenjie.album.Album
import com.zf.eth.MyApplication.Companion.context
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.mvp.bean.MyOrderList
import com.zf.eth.mvp.contract.ConfirmOrderContrect
import com.zf.eth.mvp.presenter.ConfirmOrderPresenter
import com.zf.eth.showToast
import com.zf.eth.utils.Base64Utils
import kotlinx.android.synthetic.main.activity_c2c_eth3.*
import kotlinx.android.synthetic.main.layout_toolbar.*


class C2cEthThreeActivity : BaseActivity(), ConfirmOrderContrect.View {

//    override fun setBuyInfo(bean: BuyBean) {
//
//    }
//
//    override fun setConfirmPay() {
//
//    }
//
//    override fun setPayImg(url: String) {
//        mUrl = url
//        GlideUtils.loadUrlImage(context,mUrl,payImg)
//
//        showToast("上传凭证成功")
//    }

    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
    }

    override fun setConfirmOrderSuccess(msg: String) {
        showToast(msg)
        finish()
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

    companion object {
        fun actionStart(context: Context?, mData: MyOrderList) {
            val intent = Intent(context, C2cEthThreeActivity::class.java)
            intent.putExtra("mData", mData)
            context?.startActivity(intent)

        }
    }

    override fun initToolBar() {
        titleName.text = "买入ETH"
        titleName.textSize = 22f
        titleName.paint.isFakeBoldText = true
        titleBackground.setBackgroundResource(R.drawable.bg1)

        backLayout.setOnClickListener {
            finish()
        }
    }

    override fun layoutId(): Int = R.layout.activity_c2c_eth3

    private val mTitle = arrayOf("微信", "支付宝", "银行")

    private val mAdapter by lazy { ArrayAdapter(context, android.R.layout.simple_spinner_item, mTitle) }

    private val presenter by lazy { ConfirmOrderPresenter() }


    private var mUrl = ""

    private var mData: MyOrderList? = null

    override fun initData() {
        mData = intent.getSerializableExtra("mData") as MyOrderList
    }

    override fun initView() {
        presenter.attachView(this)

        //订单号
        order_id.text = mData?.id
        //挂卖人
        order_openid.text = mData?.nickname
        //挂卖单价
        order_price.text = mData?.price
        //挂卖数量
        order_sum.text = mData?.trx
        //待付款
        order_money.text = mData?.money
        //付款人
        payee.text = mData?.nickname2
        //支付凭证

        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = mAdapter
    }

    override fun initEvent() {
        /**下拉列表点击事件*/
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (mAdapter.getItem(position) == "银行") {
                    pay_ly.visibility = View.GONE
                    bank_ly.visibility = View.VISIBLE
                } else {
                    pay_ly.visibility = View.VISIBLE
                    bank_ly.visibility = View.GONE
                }

            }

        }

        appeal_btn.setOnClickListener {
            C2cComplainActivity.actionStart(this, mData?.id)
        }

        //点击复制按钮
        copy_yh.setOnClickListener {
            val cm = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            cm.text = yh_name.text
            showToast("已复制银行名字")

        }
        copy_hz.setOnClickListener {
            val cm = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            cm.text = hz_name.text
            showToast("已复制户主名字")
        }
        copy_kh.setOnClickListener {
            val cm = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            cm.text = kh_name.text
            showToast("已复制银行卡号")
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
//                        presenter.requestPayImg(base64)
                        //是否需要转换urlEncode？
                    }
                    .start()
        }
        //点击确实付款
        confirm_btn.setOnClickListener {
            if (mUrl != "") {
                presenter.requestConfirmOrder(mData?.id ?: "", mUrl)
            } else {
                showToast("请上传凭证")
            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
//        mPresenter.detachView()
    }

    override fun start() {

    }

}


