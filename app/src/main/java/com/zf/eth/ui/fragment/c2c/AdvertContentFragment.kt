package com.zf.eth.ui.fragment.c2c

import android.text.Editable
import android.text.TextWatcher
import com.zf.eth.R
import com.zf.eth.base.BaseFragment
import com.zf.eth.mvp.contract.HangonsaleContract
import com.zf.eth.mvp.presenter.HangonsalePresenter
import com.zf.eth.showToast
import kotlinx.android.synthetic.main.fragment_c2c_advert_content.*

class AdvertContentFragment : BaseFragment(), HangonsaleContract.View {
    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
    }

    override fun setHangonsaleSuccess(msg: String) {
        showToast(msg)
        activity?.finish()
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

    companion object {
        const val BUY = "0"
        const val SELL = "1"
        fun getInstance(type: String): AdvertContentFragment {
            val fragment = AdvertContentFragment()
            fragment.mType = type
            return fragment
        }
    }

    private var mType = ""
    override fun getLayoutId(): Int = R.layout.fragment_c2c_advert_content

    private var mMoney: Double = 0.0
    private var mSum: Double = 0.0

    private val persenter by lazy { HangonsalePresenter() }


    override fun initView() {
        persenter.attachView(this)
        if (mType == "0") {
            type_name.text = "买入数量"
            edit_money.hint = "请输入买入的价格"
            edit_sum.hint = "请输入买入的数量"
            determine_btn.text = "确认买入"
        } else {
            type_name.text = "卖出数量"
            edit_money.hint = "请输入卖出的价格"
            edit_sum.hint = "请输入卖出的数量"
            determine_btn.text = "确认卖出"
        }
        sum.keyListener = null
        money.keyListener = null

    }

    override fun lazyLoad() {

    }

    override fun initEvent() {
        //输入文字监听

        edit_money.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (edit_money.text.toString() != "") mMoney = edit_money.text.toString().toDouble()
                money.setText((mMoney * mSum).toString())
            }

        })
        edit_sum.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (edit_sum.text.toString() != "") mSum = edit_sum.text.toString().toDouble()
                money.setText((mMoney * mSum).toString())
                sum.setText((mSum - (mSum * 0.01)).toString())
            }

        })

        /**确认买入卖出网络请求*/
        determine_btn.setOnClickListener {
            if (mMoney in 3.6..4.4) {
                persenter.requesHangonsale(
                    mType,
                    mMoney.toString(),
                    (mMoney * mSum).toString(),
                    ((mMoney * mSum) * 0.01).toString(),
                    mSum.toString(),
                    (mMoney * mSum).toString()
                )
            } else {
                showToast("请按参考价格来输入价格")
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        persenter.detachView()
    }
}