package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.utils.GlideUtils
import com.zf.eth.utils.StatusBarUtils
import kotlinx.android.synthetic.main.activity_see_img.*

class SeeImgActivity : BaseActivity() {
    companion object {
        fun actionStart(context: Context?, img: String?) {
            val intent = Intent(context, SeeImgActivity::class.java)
            intent.putExtra("img", img)
            context?.startActivity(intent)
        }
    }

    override fun initToolBar() {
        StatusBarUtils.darkMode(
            this,
            ContextCompat.getColor(this, R.color.colorSecondText),
            0.3f
        )
    }

    override fun layoutId(): Int = R.layout.activity_see_img
    var mImg = ""
    override fun initData() {
        mImg = intent.getStringExtra("img")
    }

    override fun initView() {
        GlideUtils.loadUrlImage(this, mImg, photo_img)
        photo_img.enable()
        photo_img.maxScale = 2.0F
    }

    override fun initEvent() {
        photo_img.setOnClickListener {
            finish()
        }
    }

    override fun start() {
        overridePendingTransition(R.anim.push_bottom_in, 0)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, R.anim.push_bottom_out)
    }
}