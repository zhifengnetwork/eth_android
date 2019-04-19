package com.zf.eth.ui.adapter

import android.content.Context
import android.graphics.Color
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zf.eth.R
import com.zf.eth.mvp.bean.MyOrderList
import com.zf.eth.ui.activity.C2cEthOneActivity
import com.zf.eth.ui.activity.C2cEthThreeActivity
import com.zf.eth.ui.activity.C2cEthTwoActivity
import com.zf.eth.utils.TimeUtils
import kotlinx.android.synthetic.main.item_c2c_order.view.*
import android.util.SparseArray


class OrderContentAdapter(val context: Context?, val data: List<MyOrderList>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val mData = data
    //用于退出 Activity,避免 Countdown，造成资源浪费。
    private var countDownCounters: SparseArray<CountDownTimer>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_c2c_order, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.apply {
            if (mData[position].type == "1") {
                type_name.text = "买入"
                type_name.setTextColor(Color.parseColor("#70c376"))

            } else {
                type_name.text = "卖出"
                type_name.setTextColor(Color.parseColor("#ce2f50"))
            }

            when (mData[position].status) {
                "0" -> {
                    status_name.text = "未交易"
                    if (mData[position].type == "0") {
                        type_name.text = "买入"
                        type_name.setTextColor(Color.parseColor("#70c376"))
                    } else {
                        type_name.text = "卖出"
                        type_name.setTextColor(Color.parseColor("#ce2f50"))
                    }
                    skip_ly.setOnClickListener {
                        C2cEthOneActivity.actionStart(context, mData[position])
                    }
                }
                "1" -> {
                    status_name.text = "交易中"
                    if (mData[position].type == "1") {
                        skip_ly.setOnClickListener {
                            C2cEthThreeActivity.actionStart(context, mData[position])
                        }
                    } else {
                        skip_ly.setOnClickListener {
                            C2cEthTwoActivity.actionStart(context, mData[position])
                        }
                    }
                    countdown.visibility = View.VISIBLE
                    /**开启定时器*/
                    //当前时间戳
                    val currentTime = System.currentTimeMillis()
                    if (currentTime < mData[position].apple_time * 1000) {
                        val tickTime = (mData[position].apple_time * 1000) - currentTime
                        val countDownTimer = object : CountDownTimer(tickTime, 1000) {
                            override fun onFinish() {
                                apple_time.text="订单已超时"
                                apple_time.setTextColor(Color.parseColor("#ce2f50"))
                            }

                            override fun onTick(millisUntilFinished: Long) {
                                apple_time.text = TimeUtils.getCountTime2(millisUntilFinished)
                            }
                        }
                        countDownTimer.start()
                        //将此 countDownTimer 放入list.
                        countDownCounters?.put(apple_time.hashCode(), countDownTimer)
                    } else {
                        //过时，订单作废

                    }
                }
                "2" -> {
                    status_name.text = "交易完成"
                    skip_ly.setOnClickListener {
                        C2cEthTwoActivity.actionStart(context, mData[position])
                    }
                }
                "3" -> {
                    if (mData[position].type == "0") {
                        type_name.text = "买入"
                        type_name.setTextColor(Color.parseColor("#70c376"))
                    } else {
                        type_name.text = "卖出"
                        type_name.setTextColor(Color.parseColor("#ce2f50"))
                    }
                    status_name.text = "交易失败"
                    skip_ly.setOnClickListener {
                        C2cEthTwoActivity.actionStart(context, mData[position])
                    }
                }

            }
            trx_sum.text = mData[position].trx
            price.text = mData[position].price
            money.text = mData[position].money
            time.text = mData[position].datatime


        }

    }

    /**
     * 清空当前 CountTimeDown 资源
     */
    fun cancelAllTimers() {
        if (countDownCounters == null) {
            return
        }
        for (i in 0 until countDownCounters!!.size()) {
            val cdt = countDownCounters?.get(countDownCounters!!.keyAt(i))

            if (cdt == null) {
                cdt?.cancel()
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}