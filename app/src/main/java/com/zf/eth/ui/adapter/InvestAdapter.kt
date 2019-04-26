package com.zf.eth.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zf.eth.R
import com.zf.eth.mvp.bean.InvestList
import kotlinx.android.synthetic.main.item_invest.view.*

class InvestAdapter(val context: Context?, val data: List<InvestList>) :
        RecyclerView.Adapter<InvestAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_invest, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.apply {
            money1.text = data[position].money
            time1.text = data[position].createtime
            title1.text = data[position].title
            status1.text = when (data[position].status) {
                "0" -> "审核中"
                "1" -> "成功"
                else -> ""
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}