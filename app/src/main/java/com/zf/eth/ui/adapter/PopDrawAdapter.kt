package com.zf.eth.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zf.eth.R
import com.zf.eth.mvp.bean.LotteryNumber
import com.zf.eth.utils.TimeUtils
import kotlinx.android.synthetic.main.item_pop_draw.view.*

class PopDrawAdapter(val context: Context, val data: List<LotteryNumber>) :
    RecyclerView.Adapter<PopDrawAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_pop_draw, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            time.text = data[position].time
            num.text = data[position].number
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}