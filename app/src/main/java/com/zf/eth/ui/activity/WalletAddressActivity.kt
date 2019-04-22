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
import kotlinx.android.synthetic.main.activity_walet_address.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * 钱包地址
 */
class WalletAddressActivity : BaseActivity(), PayManageContract.View {

    //下面两个回调在paymentActivity才用到
    override fun setWechatImg(url: String) {
    }

    override fun setAlipayImg(url: String) {
    }

    private var mUrl = ""

    override fun setAddressImg(url: String) {
        mUrl = url
        GlideUtils.loadUrlImage(this, url, image)
    }

    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
    }

    override fun getPay(bean: PayManageBean) {

        if (bean.walletaddress.isNotEmpty()) {
            walletAddress.setText(bean.walletaddress)
            bean.walletcode?.let {
                if (it.isNotEmpty()) {
                    GlideUtils.loadUrlImage(this, bean.walletcode, image)
                    mUrl = bean.walletcode
                }
            }

            confirm.isSelected = true
            confirm.text = "修改"
            walletAddress.isEnabled = false
            image.isEnabled = false
        } else {
            confirm.isSelected = false
            confirm.text = "确定"
            walletAddress.isEnabled = true
            image.isEnabled = true
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
        fun actionStart(context: Context?) {
            context?.startActivity(Intent(context, WalletAddressActivity::class.java))
        }
    }

    override fun initToolBar() {
        titleName.text = "钱包地址"
        backLayout.setOnClickListener { finish() }
    }

    private val presenter by lazy { PayManagePresenter() }

    override fun layoutId(): Int = R.layout.activity_walet_address

    override fun initData() {

    }

    override fun initView() {
        presenter.attachView(this)
    }

    override fun initEvent() {

        confirm.setOnClickListener {

            if (confirm.isSelected) {
                confirm.isSelected = false
                confirm.text = "确定"
                walletAddress.isEnabled = true
                image.isEnabled = true
            } else {
                if (walletAddress.text.isEmpty() || mUrl.isEmpty()) {
                    showToast("请先完善信息")
                } else {
                    presenter.requestEditPayManege(
                            walletAddress.text.toString(),
                            mUrl, "", "", "", "", ""
                    )
                }
            }
        }

        image.setOnClickListener {
            Album.image(this)
                    .multipleChoice()
                    .camera(true)
                    .columnCount(3)
                    .selectCount(1)
                    .onResult {
                        val base64 = Base64Utils.bitmapToString(it[0].path)
                        presenter.requestUpImg(base64, null)
                    }
                    .start()
        }
    }

    override fun start() {
        presenter.requestPay()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

}