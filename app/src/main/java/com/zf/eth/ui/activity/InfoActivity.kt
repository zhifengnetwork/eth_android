package com.zf.eth.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.Gravity
import android.widget.LinearLayout
import com.yanzhenjie.durban.Controller
import com.yanzhenjie.durban.Durban
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.utils.DurbanUtils
import com.zf.eth.utils.LogUtils
import com.zf.eth.view.popwindow.AvatarPopupWindow
import kotlinx.android.synthetic.main.activity_info.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * 个人资料
 */
class InfoActivity : BaseActivity() {

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

    override fun initData() {
    }

    override fun initView() {
    }

    override fun initEvent() {
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
                    val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
                    val imgBody = RequestBody.create(
                            MediaType.parse("multipart/form-data"),
                            file
                    )
                    builder.addFormDataPart("image_file", file.name, imgBody)
                    val imageBodyPart = MultipartBody.Part.createFormData(
                            "image" //约定key
                            , System.currentTimeMillis().toString() + ".png" //后台接收的文件名
                            , imgBody
                    )
                    LogUtils.e(">>>>>:$imageBodyPart")
//                    updateUserInfoPresenter.upLoadHead(imageBodyPart)
                }
            }

        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun start() {
    }
}