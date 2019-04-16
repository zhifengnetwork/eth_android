package com.zf.eth.ui.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zf.eth.R
import com.zf.eth.mvp.bean.MyOrderBean
import com.zf.eth.ui.activity.C2cEthOneActivity
import com.zf.eth.ui.activity.C2cEthThreeActivity
import com.zf.eth.ui.activity.C2cEthTwoActivity
import kotlinx.android.synthetic.main.item_c2c_order.view.*

class OrderContentAdapter(val context:Context?,val data:List<MyOrderBean>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private val mData=data
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_c2c_order, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.apply {
            if (mData[position].type=="1"){
                type_name.text="买入"
                type_name.setTextColor(Color.parseColor("#70c376"))

            }else {
                type_name.text = "卖出"
                type_name.setTextColor(Color.parseColor("#ce2f50"))
            }

                when(mData[position].status){
                "0" -> {
                    status_name.text="未交易"
                    if (mData[position].type=="0"){
                        type_name.text="买入"
                        type_name.setTextColor(Color.parseColor("#70c376"))
                    }else{
                        type_name.text="卖出"
                        type_name.setTextColor(Color.parseColor("#ce2f50"))
                    }
                    skip_ly.setOnClickListener {
                        C2cEthOneActivity.actionStart(context,mData[position])
                    }
                }
                "1" -> {
                    status_name.text="交易中"
                    if (mData[position].type=="1"){
                        skip_ly.setOnClickListener {
                            C2cEthThreeActivity.actionStart(context,mData[position])
                        }
                    }else{
                        skip_ly.setOnClickListener {
                            C2cEthTwoActivity.actionStart(context,mData[position])
                        }
                    }
                    countdown.visibility=View.VISIBLE

                }
                "2" -> {
                    status_name.text="交易完成"
                    skip_ly.setOnClickListener {
                        C2cEthTwoActivity.actionStart(context, mData[position])
                    }
                }
                "3" -> {
                    status_name.text="交易失败"
                    skip_ly.setOnClickListener {
                        C2cEthTwoActivity.actionStart(context, mData[position])
                    }
                }

            }
            trx_sum.text=mData[position].trx
            price.text=mData[position].price
            money.text=mData[position].money
            time.text=mData[position].datatime



        }

    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}