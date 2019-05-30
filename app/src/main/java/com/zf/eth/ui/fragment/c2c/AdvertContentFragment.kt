package com.zf.eth.ui.fragment.c2c

import android.annotation.SuppressLint
import androidx.core.widget.addTextChangedListener
import com.zf.eth.R
import com.zf.eth.base.BaseFragment
import com.zf.eth.mvp.bean.Ether
import com.zf.eth.mvp.contract.HangonsaleContract
import com.zf.eth.mvp.presenter.HangonsalePresenter
import com.zf.eth.showToast
import com.zf.eth.utils.PriceInputFilter
import kotlinx.android.synthetic.main.fragment_c2c_advert_content.*
import java.math.BigDecimal
import java.text.NumberFormat


class AdvertContentFragment : BaseFragment(), HangonsaleContract.View {

    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
    }

    override fun setHangonsaleSuccess(msg: String) {
        showToast(msg)
        activity?.finish()
    }

    //获得手续费
    override fun getEther(bean: Ether) {
        setEther(bean)
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
    //输入的金钱
    private lateinit var mMoney: BigDecimal
    //输入的数量
    private lateinit var mSum: BigDecimal
    //扣除的手续费用
    private lateinit var hundred: BigDecimal
    //    private val hundred = BigDecimal("0.01")
    //参考价格浮动 百分之10
    private val swim = BigDecimal("0.1")
    //最低价格
    private lateinit var minMoney: BigDecimal
    //最高价格
    private lateinit var maxMoney: BigDecimal

    private val presenter by lazy { HangonsalePresenter() }


    @SuppressLint("SetTextI18n")
    override fun initView() {
        presenter.attachView(this)
        if (mType == "0") {
            type_name.text = "买入数量"
            one_title.text = "预获币数(ETH)"
            two_title.text = "预付金额(CNY)"
            edit_money.hint = "请输入买入的价格"
            edit_sum.hint = "请输入买入的数量"
            determine_btn.text = "确认买入"
        } else {
            type_name.text = "卖出数量"
            one_title.text = "预获金额(CNY)"
            two_title.text = "预付币数(ETH)"
            edit_money.hint = "请输入卖出的价格"
            edit_sum.hint = "请输入卖出的数量"
            determine_btn.text = "确认卖出"
        }
        sum.keyListener = null
        money.keyListener = null
        //输入框限制
        edit_money.filters = arrayOf(PriceInputFilter())
        edit_sum.filters = arrayOf(PriceInputFilter())
    }

    override fun lazyLoad() {
        presenter.requestEther()
    }

    @SuppressLint("SetTextI18n")
    override fun initEvent() {
        if (mType == "0") {
            /**--------买入发布---------*/
            //输入文字监听
            edit_money.addTextChangedListener {
                if (edit_money.text.isNotEmpty() && edit_sum.text.isNotEmpty()) {
//                subtract：减号   multiply：乘号 divide：除号
                    mMoney = BigDecimal(edit_money.text.toString())
                    mSum = BigDecimal(edit_sum.text.toString())
                    //预获币数
                    sum.setText(mSum.subtract(mSum.multiply(hundred)).toString())
                    //预付金额
                    money.setText(mMoney.multiply(mSum).toString())

                }
            }
            //输入数量监听
            edit_sum.addTextChangedListener {
                if (edit_money.text.isNotEmpty() && edit_sum.text.isNotEmpty()) {
                    mMoney = BigDecimal(edit_money.text.toString())
                    mSum = BigDecimal(edit_sum.text.toString())
                    //预付金额
                    money.setText(mMoney.multiply(mSum).toString())
                    //预获币数
                    sum.setText(mSum.subtract(mSum.multiply(hundred)).toString())
                }
            }
            /**确认买入卖出网络请求*/
            determine_btn.setOnClickListener {
                when {
                    edit_money.text.toString() == "" -> showToast("请输入价格")
                    edit_sum.text.toString() == "" -> showToast("请输入数量")
                    else -> {
                        if (mMoney.toDouble() in minMoney.toDouble()..maxMoney.toDouble()) {
                            //金额 预获币数 手续费 数量 预付金额
                            presenter.requesHangonsale(
                                mType,
                                mMoney.toString(),
                                mSum.subtract(mSum.multiply(hundred)).toString(),
                                mMoney.multiply(mSum).multiply(hundred).toString(),
                                mSum.toString(),
                                mMoney.multiply(mSum).toString()
                            )
                        } else {
                            showToast("请按参考价格来输入价格")
                        }
                    }
                }
            }
        } else {
            /**------------卖出发布-----------*/
            //输入文字监听
            edit_money.addTextChangedListener {
                if (edit_money.text.isNotEmpty() && edit_sum.text.isNotEmpty()) {
//                subtract：减号   multiply：乘号 divide：除号
                    mMoney = BigDecimal(edit_money.text.toString())
                    mSum = BigDecimal(edit_sum.text.toString())
                    //预获金额
                    sum.setText(mMoney.multiply(mSum).toString())
                    //代付币数
                    money.setText(mSum.add(mSum.multiply(hundred)).toString())
                }
            }
            //输入数量监听
            edit_sum.addTextChangedListener {
                if (edit_money.text.isNotEmpty() && edit_sum.text.isNotEmpty()) {
//                subtract：减号   multiply：乘号 divide：除号
                    mMoney = BigDecimal(edit_money.text.toString())
                    mSum = BigDecimal(edit_sum.text.toString())
                    //预获金额
                    sum.setText(mMoney.multiply(mSum).toString())
                    //代付币数
                    money.setText(mSum.add(mSum.multiply(hundred)).toString())
                }
            }
            /**确认买入卖出网络请求*/
            determine_btn.setOnClickListener {
                when {
                    edit_money.text.toString() == "" -> showToast("请输入价格")
                    edit_sum.text.toString() == "" -> showToast("请输入数量")
                    else -> {
                        if (mMoney.toDouble() in minMoney.toDouble()..maxMoney.toDouble()) {
                            //金额 预获金钱 手续费 数量 代付金币
                            presenter.requesHangonsale(
                                mType,
                                mMoney.toString(),
                                mMoney.subtract(mSum).toString(),
                                mSum.subtract(hundred).toString(),
                                mSum.toString(),
                                mSum.add(mSum.multiply(hundred)).toString()
                            )
                        } else {
                            showToast("请按参考价格来输入价格")
                        }
                    }
                }

            }
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    @SuppressLint("SetTextI18n")
    private fun setEther(bean: Ether) {
        //参考价格  最低价 最高价
        val money = BigDecimal(bean.trxprice)
        val mDivisor = BigDecimal("100")
        //最低价
        minMoney = money.subtract(money.multiply(swim))
        //最高价格
        maxMoney = money.add(money.multiply(swim))
        //手续费
        hundred = BigDecimal(bean.trxsxf).divide(mDivisor)

        //界面赋值
        min.text = minMoney.toString()
        max.text = maxMoney.toString()

        //去掉多余的 0
        val nf = NumberFormat.getInstance()
        trxsxf.text = "手续费:${nf.format(bean.trxsxf)}%"


    }

}