package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.mvp.contract.GameRulesContract
import com.zf.eth.mvp.presenter.GameRulesPresenter
import com.zf.eth.showToast
import com.zzhoujay.richtext.RichText
import kotlinx.android.synthetic.main.activity_game_rules.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class GameRulesActivity : BaseActivity(), GameRulesContract.View {

    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
    }

    override fun setGameRules(txt: String) {
        RichText.fromHtml(txt).into(textView)
    }

    override fun showLoading() {
        showLoadingDialog()
    }

    override fun dismissLoading() {
        dismissLoadingDialog()
    }

    companion object {
        fun actionStart(context: Context?) {
            context?.startActivity(Intent(context, GameRulesActivity::class.java))
        }
    }

    override fun start() {
        presenter.requestGameRules()
    }

    override fun layoutId(): Int = R.layout.activity_game_rules

    private val presenter by lazy { GameRulesPresenter() }

    override fun initView() {
        presenter.attachView(this)
        RichText.initCacheDir(this)
        RichText.debugMode = true
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
        RichText.clear(this)
    }

    override fun initToolBar() {
        backLayout.setOnClickListener { finish() }
        titleName.text = "游戏规则"
    }

    override fun initEvent() {

    }

    override fun initData() {

    }

}