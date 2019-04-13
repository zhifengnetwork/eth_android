package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import com.zf.eth.R
import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseActivity
import com.zf.eth.mvp.bean.LoginResult
import com.zf.eth.mvp.contract.LoginContract
import com.zf.eth.mvp.presenter.LoginPresenter
import com.zf.eth.showToast
import com.zf.eth.utils.Preference
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginContract.View {

    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
    }

    private var userId by Preference(UriConstant.USER_ID, "")

    override fun setLogin(bean: LoginResult) {
        userId = bean.user_id
        val intent = Intent(this, MainActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    companion object {
        fun actionStart(context: Context?) {
            context?.startActivity(Intent(context, LoginActivity::class.java))
        }
    }

    override fun initToolBar() {

    }

    override fun layoutId(): Int = R.layout.activity_login

    private val presenter by lazy { LoginPresenter() }

    override fun initData() {
    }

    override fun initView() {
        presenter.attachView(this)

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun initEvent() {

        login.setOnClickListener {
            if (phoneNum.text.isEmpty() || passWord.text.isEmpty()) {
                showToast("请先输入手机号和密码")
            } else {
                presenter.requestLogin(phoneNum.text.toString(), passWord.text.toString())
            }
        }

        register.setOnClickListener {
            RegisterActivity.actionStart(this)
        }

        findPwd.setOnClickListener {
            FindPwdActivity.actionStart(this)
        }
    }

    override fun start() {
    }
}