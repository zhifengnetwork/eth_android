package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.mvp.contract.ComplainContract
import com.zf.eth.mvp.presenter.ComplainPresenter
import com.zf.eth.showToast
import kotlinx.android.synthetic.main.activity_c2c_complain.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class C2cComplainActivity : BaseActivity(), ComplainContract.View {
    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
    }

    override fun setComplainSuccess(msg: String) {
        showToast(msg)
        finish()
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

    companion object {
        fun actionStart(context: Context?, id: String?) {
            val intent = Intent(context, C2cComplainActivity::class.java)
            intent.putExtra("id", id)
            context?.startActivity(intent)
        }
    }

    override fun initToolBar() {
        titleName.text = "申诉"
        titleName.textSize = 22f
        titleName.paint.isFakeBoldText = true
        titleBackground.setBackgroundResource(R.drawable.bg1)
        backLayout.setOnClickListener {
            finish()
        }
    }

    override fun layoutId(): Int = R.layout.activity_c2c_complain

    private val presenter by lazy { ComplainPresenter() }

    private var id = ""

    override fun initData() {
        id = intent.getStringExtra("id")
    }

    override fun initView() {
        presenter.attachView(this)

    }

    override fun initEvent() {
        add_btn.setOnClickListener {
            //文件
            val files = ""
            //申诉标题
            val text = complainant_name.text.toString()
            //申诉内容
            val textArea = complainant_text.text.toString()

            if (text=="") showToast("请输入申诉标题")

            if (textArea=="") showToast("请输入申诉内容")

            if(text!=""&&textArea!="") presenter.requesComplain(id, files, text, textArea)


        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun start() {

    }

}