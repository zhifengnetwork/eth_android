package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import android.util.Log
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.mvp.bean.PayManageBean
import com.zf.eth.mvp.contract.PayManageContract
import com.zf.eth.mvp.presenter.PayManagePresenter
import kotlinx.android.synthetic.main.activity_payment.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class PaymentActivity : BaseActivity(),PayManageContract.View{


    override fun showError(msg: String, errorCode: Int) {

    }
    override fun getPay(bean: PayManageBean) {
        Log.e("检测","setPay执行,bean:"+bean)

        card_number.setText(bean.bankid)
        user_name.setText(bean.bankid)
        bank_name.setText(bean.bankname)

    }
    override fun editPaySuccess() {
        Log.e("检测","editPaySuccess执行")
    }
    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

    companion object {
        fun actionStart(context: Context?) {
            context?.startActivity(Intent(context, PaymentActivity::class.java))
        }
    }

    override fun initToolBar() {
        titleName.text = "支付管理"

    }

    private val presenter by lazy { PayManagePresenter() }


    override fun layoutId(): Int = R.layout.activity_payment

    override fun initData() {

    }

    override fun initView() {
        presenter.attachView(this)


    }

    override fun initEvent() {
        edit_btn.setOnClickListener {
            val zfbFile=""
            val wxFile=""
            Log.e("检测","确定按钮点击了")
//            presenter.requestEditPayManege("","",zfbFile,wxFile,card_number.text.toString(),user_name.text.toString(),bank_name.text.toString())
            presenter.requestEditPayManege("","",zfbFile,wxFile,"123456","建设","建设银行")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
    override fun start() {
        Log.e("检测","requestPay请求")
        presenter.requestPay()
    }


}