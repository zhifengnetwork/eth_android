package com.zf.eth.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zf.eth.R
import com.zf.eth.mvp.bean.C2cList
import kotlinx.android.synthetic.main.item_c2c_content.view.*

class C2cContentAdapter(val context: Context?, val list: List<C2cList>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val c2cList = list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_c2c_content, parent, false)
        //记录卖出的数量
        //记录买入的数量
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return c2cList.size
    }

    var mClickListener: ((C2cList) -> Unit)? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.apply {
            number.text = "挂单编号：" + c2cList[position].id
            openid.text = "挂单人：" + c2cList[position].nickname
            price.text = "￥：" + c2cList[position].price
            //挂单数量
            sum.text = c2cList[position].trx
            //支付方式判断可不可见
            alipay.visibility = if (c2cList[position].zfbfile !="1") View.GONE else View.VISIBLE

            wechat.visibility = if (c2cList[position].wxfile != "1") View.GONE else View.VISIBLE

            bankcard.visibility=if (c2cList[position].bank != "1") View.GONE else View.VISIBLE
            //判断按钮的文字
            item_tv.text=if (c2cList[position].type =="1") "买入" else "卖出"
            //按钮的点击监听
            item_tv.setOnClickListener {
                mClickListener?.invoke(c2cList[position])
            }


        }


    }

    fun init() {

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}