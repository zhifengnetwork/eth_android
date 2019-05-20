package com.zf.eth.ui.activity

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.os.Handler
import android.provider.MediaStore
import android.util.Log
import android.view.Gravity
import android.widget.LinearLayout
import com.zf.eth.MyApplication
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.mvp.bean.BulletinDetailBean
import com.zf.eth.mvp.contract.BulletinDetailContract
import com.zf.eth.mvp.presenter.BulletinDetailPresenter
import com.zf.eth.showToast
import com.zf.eth.utils.PermissionsUtil
import com.zf.eth.view.popwindow.RegionPopupWindow
import com.zzhoujay.richtext.RichText
import kotlinx.android.synthetic.main.activity_notice_content.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import kotlinx.android.synthetic.main.pop_notice.view.*
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


class NoticeContentActivity : BaseActivity(), PermissionsUtil.IPermissionsCallback, BulletinDetailContract.View {
    override fun onPermissionsGranted(requestCode: Int, permissions: Array<out String>) {
        //保存图片必须在子线程中操作，是耗时操作
        Thread(Runnable {
            mHandler.obtainMessage(SAVE_BEGIN).sendToTarget()
            val bp = returnBitMap(mImg)
            saveImageToPhotos(MyApplication.context, bp)
        }).start()
    }

    override fun onPermissionsDenied(requestCode: Int, permissions: Array<String>) {

    }

    override fun showError(msg: String, errorCode: Int) {

    }

    override fun getBulletinDetail(bean: BulletinDetailBean) {
        //标题
        titles.text=bean.article.article_title
        //时间
        time.text=bean.article.article_date_v
        //作者
        user_name.text=bean.article.article_author
        //内容
        RichText.fromHtml(bean.article.article_content)
            .imageClick { imageUrls, position ->
                popWindow(imageUrls[position])
            }
            .into(textView)
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

    private var mId = ""

    private var mImg = ""
    private val presenter by lazy { BulletinDetailPresenter() }

    private var permissionsUtil: PermissionsUtil? = null

    //保存图片pop弹窗
    private lateinit var mPopWindow: RegionPopupWindow

    private val SAVE_SUCCESS = 0//保存图片成功
    private val SAVE_FAILURE = 1//保存图片失败
    private val SAVE_BEGIN = 2//开始保存图片
    private val mHandler = Handler {
        when (it.what) {
            SAVE_SUCCESS -> {
                showToast("保存成功")
                mPopWindow.onDismiss()
                true
            }
            SAVE_FAILURE -> {
                showToast("保存失败")
                true
            }
            SAVE_BEGIN -> {
//                Log.e("检测", ">>>开始")
                true
            }
            else -> false
        }
    }


    companion object {
        fun actionStart(context: Context?, aid: String) {
            val intent = Intent(context, NoticeContentActivity::class.java)
            intent.putExtra("id", aid)
            context?.startActivity(intent)
        }
    }

    override fun initToolBar() {
        backLayout.setOnClickListener { finish() }
        titleName.text = "详细内容"
    }

    override fun initData() {
        mId = intent.getStringExtra("id")
    }

    override fun layoutId(): Int = R.layout.activity_notice_content


    override fun initView() {
        presenter.attachView(this)

        RichText.initCacheDir(this)

        RichText.debugMode = true

    }

    override fun initEvent() {

    }

    override fun onDestroy() {
        super.onDestroy()
        RichText.clear(this)
        presenter.detachView()
    }

    override fun start() {
        presenter.requseBulletinDetail(mId)
    }


    /**
     * 将URL转化成bitmap形式
     *
     * @param url
     * @return bitmap type
     */
    private fun returnBitMap(url: String): Bitmap? {
        val myFileUrl: URL
        var bitmap: Bitmap? = null
        try {
            myFileUrl = URL(url)
            val conn: HttpURLConnection = myFileUrl.openConnection() as HttpURLConnection
            conn.doInput = true
            conn.connect()
            val aaa = conn.inputStream
            bitmap = BitmapFactory.decodeStream(aaa)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return bitmap
    }

    /**
     * 保存图片到本地相册
     */
    private fun saveImageToPhotos(context: Context?, bmp: Bitmap?) {
        // 首先保存图片
        val appDir = File(Environment.getExternalStorageDirectory(), "ETH")
        if (!appDir.exists()) {
            appDir.mkdir()
        }
        val fileName = System.currentTimeMillis().toString() + ".jpg"
        val file = File(appDir, fileName)
        try {
            val fos = FileOutputStream(file)
            bmp?.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            fos.flush()
            fos.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(
                context?.contentResolver,
                file.absolutePath, fileName, null
            )
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            mHandler.obtainMessage(SAVE_FAILURE).sendToTarget()
            return
        }
        // 最后广播通知图库更新
        val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        val uri = Uri.fromFile(file)
        intent.data = uri
        context?.sendBroadcast(intent)
        mHandler.obtainMessage(SAVE_SUCCESS).sendToTarget()
    }

    /**长按保存图片跳出弹窗*/
    private fun popWindow(img: String) {
        mPopWindow = object : RegionPopupWindow(
            this, R.layout.pop_notice,
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ) {
            override fun initView() {
                contentView?.apply {
                    //保存按钮
                    preserve.setOnClickListener {
                        permissionsUtil = PermissionsUtil
                            .with(context as Activity)
                            .requestCode(0)
                            .isDebug(true)
                            .permissions(PermissionsUtil.Permission.Storage.WRITE_EXTERNAL_STORAGE)
                            .request()
                        mImg = img
                    }
                    //取消按钮
                    cancel.setOnClickListener {
                        mPopWindow.onDismiss()
                    }
                }
            }

        }
        mPopWindow.updata()
        mPopWindow.showAtLocation(parentLayout, Gravity.BOTTOM, 0, 0)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //需要调用onRequestPermissionsResult
        permissionsUtil?.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //监听跳转到权限设置界面后再回到应用
        permissionsUtil?.onActivityResult(requestCode, resultCode, data)

    }
}