package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import android.view.View
import com.yanzhenjie.album.Album
import com.zf.eth.MyApplication.Companion.context
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.mvp.contract.ComplainContract
import com.zf.eth.mvp.presenter.ComplainPresenter
import com.zf.eth.showToast
import com.zf.eth.utils.Base64Utils
import com.zf.eth.utils.GlideUtils
import kotlinx.android.synthetic.main.activity_c2c_complain.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class C2cComplainActivity : BaseActivity(), ComplainContract.View {
    override fun setPayImg(url: String) {
        files = url
        GlideUtils.loadUrlImage(context, files, pay_img)
        img_btn.visibility = View.GONE
        pay_img.visibility = View.VISIBLE
    }

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
    //文件
    private var files = ""

    override fun initData() {
        id = intent.getStringExtra("id")
    }

    override fun initView() {
        presenter.attachView(this)
        img_btn.visibility = View.VISIBLE
        pay_img.visibility = View.GONE

    }

    override fun initEvent() {
        add_btn.setOnClickListener {
            //申诉标题
            val text = complainant_name.text.toString()
            //申诉内容
            val textArea = complainant_text.text.toString()

            if (text == "") showToast("请输入申诉标题")

            if (textArea == "") showToast("请输入申诉内容")

            if (text != "" && textArea != "") presenter.requestComplain(id, files, text, textArea)


        }
        //第一次上传凭证
        select_btn.setOnClickListener {
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

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun start() {

    }

}