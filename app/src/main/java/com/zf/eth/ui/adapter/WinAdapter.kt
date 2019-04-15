package com.zf.eth.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zf.eth.R
import com.zf.eth.mvp.bean.WinList
import kotlinx.android.synthetic.main.item_win.view.*

class WinAdapter(val context: Context?, val data: List<WinList>) : RecyclerView.Adapter<WinAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_win, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            createTime.text = data[position].createtime
            num.text = data[position].id
            price.text = data[position].money
            ranking.text = data[position].ranking
            type.text = if (data[position].type == "1") "投注中奖" else "投资排名奖"
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}