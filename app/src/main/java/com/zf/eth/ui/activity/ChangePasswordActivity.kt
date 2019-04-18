package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.text.TextUtils
import com.zf.eth.R
import com.zf.eth.api.UriConstant
import com.zf.eth.base.BaseActivity
import com.zf.eth.mvp.contract.ChangePwdContract
import com.zf.eth.mvp.presenter.ChangePwdPresenter
import com.zf.eth.showToast
import com.zf.eth.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_change_password.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class ChangePasswordActivity : BaseActivity(), ChangePwdContract.View {

    override fun setVerifyCode() {
        showToast("验证码发送成功")
        if (!isRun) {
            //计时器
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

    override fun setChangePwd() {
        showToast("密码修改成功")
        finish()
    }

    override fun showLoading() {
        showLoadingDialog()
    }

    override fun dismissLoading() {
        dismissLoadingDialog()
    }

    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
    }

    companion object {
        fun actionStart(context: Context?) {
            context?.startActivity(Intent(context, ChangePasswordActivity::class.java))
        }
    }

    override fun initToolBar() {
        titleName.text = "修改密码"
        backLayout.setOnClickListener { finish() }
    }

    override fun layoutId(): Int = R.layout.activity_change_password

    private val presenter by lazy { ChangePwdPresenter() }

    override fun initData() {

    }

    override fun initView() {
        presenter.attachView(this)
    }

    private var isRun = false

    override fun initEvent() {

        confirm.setOnClickListener {
            when {
                phoneNum.text.isEmpty() -> showToast("请输入手机号码")
                code.text.isEmpty() -> showToast("请输入验证码")
                pwd.text.isEmpty() -> showToast("请输入密码")
                rePwd.text.toString() != pwd.text.toString() -> showToast("两次密码输入不一致")
                else -> presenter.requestChangePwd(phoneNum.text.toString(), code.text.toString(), pwd.text.toString())
            }

        }

        getCode.setOnClickListener {
            if (TextUtils.isEmpty(phoneNum.text)) {
                ToastUtils.showShort(this, "请输入手机号")
            } else if (!isRun) {
                presenter.requestVerifyCode(phoneNum.text.toString(), "sms_changepwd")
            }
        }

    }

    override fun start() {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

}