package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import com.yanzhenjie.album.Album
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.mvp.bean.PayManageBean
import com.zf.eth.mvp.contract.PayManageContract
import com.zf.eth.mvp.presenter.PayManagePresenter
import com.zf.eth.showToast
import com.zf.eth.utils.Base64Utils
import com.zf.eth.utils.GlideUtils
import kotlinx.android.synthetic.main.activity_payment.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class PaymentActivity : BaseActivity(), PayManageContract.View {

    //该回调在WalletAddressActivity才用到
    override fun setAddressImg(url: String) {

    }

    private var weChatImg = ""
    private var aliPayImg = ""

    override fun setAlipayImg(url: String) {
        aliPayImg = url
        showToast("上传成功")
        GlideUtils.loadUrlImage(this, aliPayImg, zfb_img)
    }

    override fun setWechatImg(url: String) {
        weChatImg = url
        showToast("上传成功")
        GlideUtils.loadUrlImage(this, weChatImg, wx_img)
    }


    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
    }

    override fun getPay(bean: PayManageBean) {

        if (bean.bankid.isNotEmpty() &&
                bean.bankname.isNotEmpty() &&
                bean.bank.isNotEmpty() &&
                bean.zfbfile.isNotEmpty() &&
                bean.wxfile.isNotEmpty()
        ) {
            edit_btn.isSelected = true
            edit_btn.text = "修改"
            card_number.isEnabled = false
            user_name.isEnabled = false
            bank_name.isEnabled = false
            zfb_img.isEnabled = false
            wx_img.isEnabled = false
            card_number.setText(bean.bankid)
            user_name.setText(bean.bankname)
            bank_name.setText(bean.bank)
            weChatImg = bean.wxfile
            aliPayImg = bean.zfbfile
            GlideUtils.loadUrlImage(this, bean.zfbfile, zfb_img)
            GlideUtils.loadUrlImage(this, bean.wxfile, wx_img)
        } else {
            edit_btn.isSelected = false
            edit_btn.text = "确定"
            card_number.isEnabled = true
            user_name.isEnabled = true
            bank_name.isEnabled = true
            zfb_img.isEnabled = true
            wx_img.isEnabled = true
        }

    }

    override fun editPaySuccess() {
        showToast("保存成功")
        finish()
    }

    override fun showLoading() {
        showLoadingDialog()
    }

    override fun dismissLoading() {
        dismissLoadingDialog()
    }

    companion object {

        const val ALIPAYIMG = "ALIPAY"
        const val WECHATIMG = "WECHAT"

        fun actionStart(context: Context?) {
            context?.startActivity(Intent(context, PaymentActivity::class.java))
        }
    }

    override fun initToolBar() {
        titleName.text = "支付管理"
        backLayout.setOnClickListener { finish() }

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
            if (edit_btn.isSelected) {
                edit_btn.text = "确定"
                edit_btn.isSelected = false
                card_number.isEnabled = true
                user_name.isEnabled = true
                bank_name.isEnabled = true
                zfb_img.isEnabled = true
                wx_img.isEnabled = true
            } else {
                if (card_number.text.isNotEmpty() && user_name.text.isNotEmpty() && bank_name.text.isNotEmpty()
                        || aliPayImg.isNotEmpty() || weChatImg.isNotEmpty()
                ) {
                    presenter.requestEditPayManege("",
                        "",
                        aliPayImg,
                        weChatImg,
                        card_number.text.toString(),
                        user_name.text.toString(),
                        bank_name.text.toString())

                } else {
                    showToast("请先完善信息")
                }
            }
        }

        zfb_img.setOnClickListener {
            Album.image(this)
                    .multipleChoice()
                    .camera(true)
                    .columnCount(3)
                    .selectCount(1)
                    .onResult {
                        val base64 = Base64Utils.bitmapToString(it[0].path)
                        presenter.requestUpImg(base64, ALIPAYIMG)
                    }
                    .start()
        }

        wx_img.setOnClickListener {
            Album.image(this)
                    .multipleChoice()
                    .camera(true)
                    .columnCount(3)
                    .selectCount(1)
                    .onResult {
                        val base64 = Base64Utils.bitmapToString(it[0].path)
                        presenter.requestUpImg(base64, WECHATIMG)
                    }
                    .start()
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun start() {
        presenter.requestPay()
    }


}