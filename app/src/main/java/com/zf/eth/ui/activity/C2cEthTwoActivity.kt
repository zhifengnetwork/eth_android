package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import com.zf.eth.MyApplication.Companion.context
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.mvp.bean.MyOrderList
import com.zf.eth.mvp.bean.OrderDetailBean
import com.zf.eth.mvp.contract.ConfirmOrderContrect
import com.zf.eth.mvp.presenter.ConfirmOrderPresenter
import com.zf.eth.showToast
import com.zf.eth.utils.GlideUtils
import kotlinx.android.synthetic.main.activity_c2c_eth2.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class C2cEthTwoActivity : BaseActivity(), ConfirmOrderContrect.View {

    override fun getOrderDetail(bean: OrderDetailBean) {
        data = bean
        dataView()
    }

    override fun setPayImg(url: String) {

    }

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

    private var id = ""

    companion object {
        fun actionStart(context: Context?, id: String) {
            val intent = Intent(context, C2cEthTwoActivity::class.java)
            intent.putExtra("id", id)
            context?.startActivity(intent)

        }
    }

    override fun initToolBar() {

        titleName.textSize = 22f
        titleName.paint.isFakeBoldText = true
        titleBackground.setBackgroundResource(R.drawable.bg1)
        backLayout.setOnClickListener {
            finish()
        }
    }

    override fun layoutId(): Int = R.layout.activity_c2c_eth2

    private var data: OrderDetailBean? = null

    private val presenter by lazy { ConfirmOrderPresenter() }

    override fun initData() {
        id = intent.getStringExtra("id")

    }

    override fun initView() {
        presenter.attachView(this)

    }

    override fun initEvent() {
        //申诉
        appeal_btn.setOnClickListener {
            C2cComplainActivity.actionStart(this, data?.list?.id)
        }
        //确认收款
        confirm_btn.setOnClickListener {
            presenter.requestConfirmOrder(data?.list?.id ?: "", "1")
        }
        payImg.setOnClickListener {
            SeeImgActivity.actionStart(this, data?.list?.file)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun start() {
        presenter.requestOrderDetail(id)
    }

    private fun dataView() {

        if (data?.list?.type == "1") titleName.text = "买入ETH" else titleName.text = "卖出ETH"

        if (data?.list?.status == "3") {
            if (data?.list?.type == "1") titleName.text = "买入ETH" else titleName.text = "卖出ETH"
        }

        if (data?.type_own == "2") {
            if (data?.list?.type == "0") openid_name.text = "挂 买 人:" else openid_name.text = "挂 卖 人:"
        } else {
            if (data?.list?.type == "0") openid_name.text = "挂 卖 人:" else openid_name.text = "挂 买 人:"
        }

        //订单号
        order_id.text = data?.list?.id
        //挂卖人
//        order_openid.text = data?.list?.nickname
        order_openid.text = data?.list?.mobile
        //挂卖单价
        order_price.text = data?.list?.price
        //挂卖数量
        order_sum.text = data?.list?.trx
        //代收款
        order_money.text = data?.list?.money
        //收款人
//        payee.text = data?.list?.nickname2
        payee.text = data?.list?.mobile2
        //支付凭证
        if (data?.list?.file != "") {
            GlideUtils.loadUrlImage(context, data?.list?.file, payImg)
            img_btn.visibility = View.GONE
            payImg.visibility = View.VISIBLE
        }
        /**交易中此页面显示确认收款按钮，修改布局文字*/
        if (data?.list?.status == "1") {
            confirm_btn.visibility = View.VISIBLE
            appeal_btn.visibility = View.VISIBLE
            money_name.text = "待 收 款："
            if (data?.type_own == "1") {
                if (data?.list?.type == "0") {
                    payee_name.text = "付 款 人："
                } else {
                    payee_name.text = "收 款 人："
                }
            } else {
                if (data?.list?.type == "1") {
                    payee_name.text = "付 款 人："
                } else {
                    payee_name.text = "收 款 人："
                }
            }
        }
        /**交易完成此页面隐藏确认收款按钮，修改布局文字*/
        if (data?.list?.status == "2") {
//            自己看自己订单
            if (data?.type_own == "1") {
                if (data?.list?.type == "0") {
                    order_openid.text = data?.list?.mobile
                    payee.text = data?.list?.mobile2
//                order_openid.text = data?.list?.nickname
//                payee.text = data?.list?.nickname2
                    money_name.text = "已 收 款："
                    payee_name.text = "付 款 人："
                } else {
                    order_openid.text = data?.list?.mobile
                    payee.text = data?.list?.mobile2
//                order_openid.text = data?.list?.nickname2
//                payee.text = data?.list?.nickname
                    money_name.text = "已 付 款："
                    payee_name.text = "收 款 人："
                }
            } else {
                if (data?.list?.type == "0") {
                    order_openid.text = data?.list?.mobile
                    payee.text = data?.list?.mobile2
//                order_openid.text = data?.list?.nickname
//                payee.text = data?.list?.nickname2
                    openid_name.text = "挂 买 人:"
                    money_name.text = "已 收 款："
                    payee_name.text = "收 款 人："
                } else {
                    order_openid.text = data?.list?.mobile
                    payee.text = data?.list?.mobile2
//                order_openid.text = data?.list?.nickname2
//                payee.text = data?.list?.nickname
                    openid_name.text = "挂 卖 人:"
                    money_name.text = "已 付 款："
                    payee_name.text = "付 款 人："
                }
            }

            confirm_btn.visibility = View.GONE
            //显示申诉按钮
            appeal_btn.visibility = View.VISIBLE
        }
        /**交易失败此页面隐藏两个按钮,修改布局的文字*/
        if (data?.list?.status == "3") {
            if (data?.list?.type == "0") {
                openid_name.text = "挂 买 人:"
                money_name.text = "待 收 款："
                payee_name.text = "收 款 人："
                //挂卖人
                order_openid.text = data?.list?.mobile2
                //收款人
                payee.text = data?.list?.mobile
            } else {
                openid_name.text = "挂 卖 人:"
                money_name.text = "待 收 款："
                payee_name.text = "付 款 人："
                //挂卖人
                order_openid.text = data?.list?.mobile
                //收款人
                payee.text = data?.list?.mobile2
                //待收款
                order_money.text = data?.list?.trx2
            }
            confirm_btn.visibility = View.GONE
            //显示申诉按钮
            appeal_btn.visibility = View.VISIBLE
        }
    }
}
