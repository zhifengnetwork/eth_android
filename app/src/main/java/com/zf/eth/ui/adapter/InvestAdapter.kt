package com.zf.eth.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zf.eth.R
import com.zf.eth.mvp.bean.InvestBean
import kotlinx.android.synthetic.main.item_invest.view.*

class InvestAdapter(val context: Context, val data: List<InvestBean>) :
    RecyclerView.Adapter<InvestAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_invest, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            money.text = data[position].money
            time.text = data[position].createtime
            title.text = data[position].title
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}