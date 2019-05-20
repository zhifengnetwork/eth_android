package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.text.TextUtils
import com.zf.eth.R
import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseActivity
import com.zf.eth.mvp.bean.RegisterBean
import com.zf.eth.mvp.contract.RegisterContract
import com.zf.eth.mvp.presenter.RegisterPresenter
import com.zf.eth.showToast
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity(), RegisterContract.View {

    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
    }

    override fun setRegister(bean: RegisterBean) {
        showToast("注册成功，请前往登陆")
        LoginActivity.actionStart(this)
        finish()
    }

    override fun setForgetPwd() {
    }


    override fun setVerifyCode() {
        showToast("验证码发送成功")
        if (!isRun) {
            val countDownTimer = object : CountDownTimer((UriConstant.SMSLIFE * 1000).toLong(), 1000) {
                override fun onFinish() {
                    isRun = false
                    getCode.text = "重新获取"
                }

                override fun onTick(millisUntilFinished: Long) {
                    isRun = true
                    getCode.text = "${(millisUntilFinished / 1000)}S"
                }
            }
            countDownTimer.start()
        }
    }

    override fun showLoading() {
        showLoadingDialog()
    }

    override fun dismissLoading() {
        dismissLoadingDialog()
    }

    companion object {
        fun actionStart(context: Context?) {
            context?.startActivity(Intent(context, RegisterActivity::class.java))
        }
    }

    override fun initToolBar() {
    }

    override fun layoutId(): Int = R.layout.activity_register

    private val presenter by lazy { RegisterPresenter() }

    override fun initData() {
    }

    override fun initView() {
        presenter.attachView(this)
    }

    private var isRun = false

    override fun initEvent() {

        //获取验证码
        getCode.setOnClickListener {
            if (TextUtils.isEmpty(phone.text)) {
                showToast("请输入手机号")
            } else if (!isRun) {
                presenter.requestVerifyCode(phone.text.toString(), "sms_reg")
            }
        }

        register.setOnClickListener {
            val phoneTxt = phone.text.toString()
            val codeTxt = code.text.toString()
            val pwdTxt = pwd.text.toString()
            val rePwdTxt = rePwd.text.toString()
            when {
                phoneTxt.isEmpty() -> showToast("请输入手机号码")
                codeTxt.isEmpty() -> showToast("请输入验证码")
                pwdTxt.isEmpty() -> showToast("请输入密码")
                rePwdTxt != pwdTxt -> showToast("两次密码输入不相同")
                else -> presenter.requestRegister("sms_reg", phoneTxt, codeTxt, pwdTxt, agentId.text.toString())
            }
        }

        findPwd.setOnClickListener {
            FindPwdActivity.actionStart(this)
        }

        login.setOnClickListener {
            LoginActivity.actionStart(this)
        }
    }

    override fun start() {
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}