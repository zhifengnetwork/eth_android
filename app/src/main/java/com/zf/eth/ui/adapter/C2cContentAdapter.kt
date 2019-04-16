package com.zf.eth.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zf.eth.R
import com.zf.eth.mvp.bean.C2cList
import kotlinx.android.synthetic.main.item_c2c_content.view.*

class C2cContentAdapter(val context: Context?, val list:ArrayList<C2cList>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    val c2cList=list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_c2c_content, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return c2cList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        holder.itemView.apply {
            number.text="挂单编号："+c2cList[position].id
            openid.text="挂单人："+c2cList[position].nickname
            price.text="￥："+c2cList[position].price
            //挂单数量
//            sum.text=c2cList[position].total
            //支付方式
            if (c2cList[position].zfbfile!="1"){
                //不可见
                alipay.visibility=View.INVISIBLE
            }
            if(c2cList[position].wxfile!="1"){
                wechat.visibility=View.INVISIBLE
            }
            if (c2cList[position].bank!="1"){
                bankcard.visibility=View.INVISIBLE
            }
            if (c2cList[position].type=="1"){
                item_tv.text="卖出"
            }else{
                item_tv.text="买入"
            }

        }
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}