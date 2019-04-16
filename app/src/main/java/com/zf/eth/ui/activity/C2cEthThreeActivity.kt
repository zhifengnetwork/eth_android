package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.zf.eth.MyApplication.Companion.context
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.mvp.bean.MyOrderBean
import kotlinx.android.synthetic.main.activity_c2c_eth3.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class C2cEthThreeActivity:BaseActivity(){
    companion object {
        fun actionStart(context: Context?, mData: MyOrderBean) {
            val intent = Intent(context, C2cEthThreeActivity::class.java)
            intent.putExtra("mData", mData)
            context?.startActivity(intent)

        }
    }
    override fun initToolBar() {
        titleName.text="买入ETH"
        titleName.textSize=22f
        titleName.paint.isFakeBoldText = true
        titleBackground.setBackgroundResource(R.drawable.bg1)

        backLayout.setOnClickListener {
            finish()
        }
    }

    override fun layoutId(): Int = R.layout.activity_c2c_eth3

    private val mTitle= arrayOf("微信","支付宝","银行")

    private val mAdapter by lazy { ArrayAdapter(context,android.R.layout.simple_spinner_item,mTitle) }

    private var mData:MyOrderBean?=null
    override fun initData() {
        mData=intent.getSerializableExtra("mData") as MyOrderBean
    }

    override fun initView() {
        //订单号
        order_id.text=mData?.id
        //挂卖人
        order_openid.text=mData?.nickname
        //挂卖单价
        order_price.text=mData?.price
        //挂卖数量
        order_sum.text=mData?.trx
        //待付款
        order_money.text=mData?.money
        //付款人
        payee.text=mData?.nickname2
        //支付凭证

        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter=mAdapter
    }

    override fun initEvent() {
        /**下拉列表点击事件*/
        spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (mAdapter.getItem(position)=="银行"){
                    pay_ly.visibility=View.GONE
                    bank_ly.visibility=View.VISIBLE
                }else{
                    pay_ly.visibility=View.VISIBLE
                    bank_ly.visibility=View.GONE
                }

            }

        }
    }

    override fun start() {

    }

}


