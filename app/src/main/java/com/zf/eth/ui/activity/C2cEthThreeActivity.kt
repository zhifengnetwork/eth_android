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
import com.zf.eth.mvp.bean.OrderDetailBean
import com.zf.eth.mvp.contract.ConfirmOrderContrect
import com.zf.eth.mvp.presenter.ConfirmOrderPresenter
import com.zf.eth.showToast
import com.zf.eth.utils.Base64Utils
import com.zf.eth.utils.GlideUtils
import kotlinx.android.synthetic.main.activity_c2c_eth3.*
import kotlinx.android.synthetic.main.layout_toolbar.*


class C2cEthThreeActivity : BaseActivity(), ConfirmOrderContrect.View {
    override fun getOrderDetail(bean: OrderDetailBean) {
        data = bean
        dataView()
    }

    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
    }

    override fun setPayImg(url: String) {
        mUrl = url
        GlideUtils.loadUrlImage(context, mUrl, payImg)
        img_btn.visibility = View.GONE
        payImg.visibility = View.VISIBLE
        showToast("上传凭证成功")
    }

    override fun setConfirmOrderSuccess(msg: String) {
        showToast(msg)
        finish()
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

    private var id = ""

    companion object {
        fun actionStart(context: Context?, id: String) {
            val intent = Intent(context, C2cEthThreeActivity::class.java)
            intent.putExtra("id", id)
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

    private val mAdapter by lazy { ArrayAdapter(context, R.layout.item_spinner, mTitle) }

    private val presenter by lazy { ConfirmOrderPresenter() }

    private var mUrl = ""

    private var data: OrderDetailBean? = null
    override fun initData() {
        id = intent.getStringExtra("id")
//        mData = intent.getSerializableExtra("mData") as MyOrderList
    }

    override fun initView() {
        presenter.attachView(this)


    }

    override fun initEvent() {
        /**下拉列表点击事件*/
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                when (mAdapter.getItem(position)) {
                    "银行" -> {
                        //银行名字
                        yh_name.text = data?.list?.bank
                        //银行户主
                        hz_name.text = data?.list?.bankname
                        //银行卡号
                        kh_name.text = data?.list?.bankid
                        pay_ly.visibility = View.GONE
                        bank_ly.visibility = View.VISIBLE
                    }
                    "微信"->{
                        GlideUtils.loadUrlImage(context, data?.list?.wxfile2, pay_img)
                        pay_ly.visibility = View.VISIBLE
                        bank_ly.visibility = View.GONE
                    }
                    "支付宝"->{
                        GlideUtils.loadUrlImage(context, data?.list?.zfbfile2, pay_img)
                        pay_ly.visibility = View.VISIBLE
                        bank_ly.visibility = View.GONE
                    }
                }

            }

        }

        appeal_btn.setOnClickListener {
            C2cComplainActivity.actionStart(this, data?.list?.id)
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

        //第一次上传支付凭证
        img_btn.setOnClickListener {
            Album.image(this)
                .multipleChoice()
                .camera(true)
                .columnCount(3)
                .selectCount(1)
                .onResult {
                    val base64 = Base64Utils.bitmapToString(it[0].path)
                    presenter.requestPayImg(base64)
                    //是否需要转换urlEncode？
                }
                .start()
        }
        //重复上传
        payImg.setOnClickListener {
            Album.image(this)
                .multipleChoice()
                .camera(true)
                .columnCount(3)
                .selectCount(1)
                .onResult {
                    val base64 = Base64Utils.bitmapToString(it[0].path)
                    presenter.requestPayImg(base64)
                    //是否需要转换urlEncode？
                }
                .start()
        }
        //点击确实付款
        confirm_btn.setOnClickListener {
            if (mUrl != "") {
                presenter.requestConfirmOrder(data?.list?.id ?: "", mUrl)
            } else {
                showToast("请上传凭证")
            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()

    }

    override fun start() {
        presenter.requestOrderDetail(id)
    }

    fun dataView() {
        //订单号
        order_id.text = data?.list?.id
        //挂卖人
        order_openid.text = data?.list?.mobile
        //挂卖单价
        order_price.text = data?.list?.price
        //挂卖数量
        order_sum.text = data?.list?.trx
        //待付款
        order_money.text = data?.list?.money
        //付款人
        payee.text = data?.list?.mobile2

        mAdapter.setDropDownViewResource(R.layout.item_spinner)
        spinner.adapter = mAdapter
    }
}


