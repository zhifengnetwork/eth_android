package com.zf.eth.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.Gravity
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import com.yanzhenjie.durban.Controller
import com.yanzhenjie.durban.Durban
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.livedata.UserInfoLiveData
import com.zf.eth.mvp.bean.UserInfoBean
import com.zf.eth.mvp.contract.ChangeInfoContract
import com.zf.eth.mvp.contract.UserInfoContract
import com.zf.eth.mvp.presenter.ChangeInfoPresenter
import com.zf.eth.mvp.presenter.UserInfoPresenter
import com.zf.eth.showToast
import com.zf.eth.utils.Base64Utils
import com.zf.eth.utils.DurbanUtils
import com.zf.eth.utils.GlideUtils
import com.zf.eth.view.popwindow.AvatarPopupWindow
import kotlinx.android.synthetic.main.activity_info.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import java.io.File

/**
 * 个人资料
 */
class InfoActivity : BaseActivity(), ChangeInfoContract.View, UserInfoContract.View {

    override fun setUserInfo(bean: UserInfoBean) {
        UserInfoLiveData.value = bean
        finish()
    }

    override fun setNotLogin() {
    }

    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
    }

    private val infoPresenter by lazy { UserInfoPresenter() }

    private var mUrl = ""

    override fun setHead(url: String) {
        mUrl = url
        GlideUtils.loadUrlImage(this, url, avatar)
        showToast("上传头像成功")
    }

    //保存成功
    override fun setChangeInfo() {
        showToast("修改成功")
        infoPresenter.requestUserInfo()
    }

    override fun showLoading() {
        showLoadingDialog()
    }

    override fun dismissLoading() {
        dismissLoadingDialog()
    }

    companion object {
        const val REQUEST_CODE = 11
        fun actionStart(context: Context?) {
            context?.startActivity(Intent(context, InfoActivity::class.java))
        }
    }

    override fun initToolBar() {
        backLayout.setOnClickListener { finish() }
        titleName.text = "头像/昵称"
    }

    override fun layoutId(): Int = R.layout.activity_info

    private val presenter by lazy { ChangeInfoPresenter() }

    override fun initData() {
    }

    override fun initView() {
        infoPresenter.attachView(this)
        presenter.attachView(this)
    }

    override fun initEvent() {

        confirm.setOnClickListener {
            if (nickName.text.isEmpty()) {
                showToast("请输入昵称")
            } else {
                presenter.requestChangeName(nickName.text.toString(), if (mUrl.isEmpty()) UserInfoLiveData.value?.member?.avatar
                        ?: "" else mUrl)
            }
        }

        avatarLayout.setOnClickListener {
            val popWindow = object : AvatarPopupWindow(
                    this,
                    R.layout.pop_avatar,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ) {}
            popWindow.showAtLocation(parentLayout, Gravity.BOTTOM, 0, 0)

            popWindow.onCamera = { it ->
                initCrop(it)
            }

            popWindow.onPhoto = {
                initCrop(it)
            }
        }
    }

    private fun initCrop(it: String) {
        val cropDirectory = DurbanUtils.getAppRootPath(this).absolutePath
        Durban.with(this)
                .inputImagePaths(it)
                .outputDirectory(cropDirectory)
                .maxWidthHeight(400, 400)
                .aspectRatio(1f, 1f)
                .compressFormat(Durban.COMPRESS_PNG)
                .compressQuality(70)
                .gesture(Durban.GESTURE_ALL)
                .controller(
                        Controller.newBuilder()
                                .enable(false)
                                .build()
                )
                .requestCode(REQUEST_CODE)
                .start()
    }

    //裁剪头像回调
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE) {
                super.onActivityResult(requestCode, resultCode, data)
                data?.let {
                    val uri: Uri = Uri.parse(Durban.parseResult(it)[0])
                    val file = File(uri.path)
                    val base64 = Base64Utils.bitmapToString(file.path)
                    presenter.requestUpHead(base64)
                }
            }

        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun start() {
        UserInfoLiveData.observe(this, Observer {
            it?.let { bean ->
                GlideUtils.loadUrlImage(this, bean.member.avatar, avatar)
                nickName.setText(bean.member.nickname)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        infoPresenter.detachView()
        presenter.detachView()
    }
}