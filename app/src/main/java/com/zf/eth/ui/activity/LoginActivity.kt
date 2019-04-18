package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import com.zf.eth.R
import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseActivity
import com.zf.eth.livedata.UserInfoLiveData
import com.zf.eth.mvp.bean.LoginBean
import com.zf.eth.mvp.bean.UserInfoBean
import com.zf.eth.mvp.contract.LoginContract
import com.zf.eth.mvp.contract.UserInfoContract
import com.zf.eth.mvp.presenter.LoginPresenter
import com.zf.eth.mvp.presenter.UserInfoPresenter
import com.zf.eth.showToast
import com.zf.eth.utils.Preference
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginContract.View, UserInfoContract.View {

    override fun setUserInfo(bean: UserInfoBean) {
        UserInfoLiveData.value = bean

        val intent = Intent(this, MainActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    override fun setNotLogin() {
    }

    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
    }

    private val infoPresenter by lazy { UserInfoPresenter() }


    private var userId by Preference(UriConstant.USER_ID, "")

    override fun setLogin(bean: LoginBean) {

        userId = bean.userid

        infoPresenter.requestUserInfo()
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
        infoPresenter.attachView(this)
        presenter.attachView(this)

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
        infoPresenter.detachView()
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