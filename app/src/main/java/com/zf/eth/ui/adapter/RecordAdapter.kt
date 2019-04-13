package com.zf.eth.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zf.eth.R
import com.zf.eth.mvp.bean.RecordList
import com.zf.eth.utils.TimeUtils
import kotlinx.android.synthetic.main.item_record.view.*

class RecordAdapter(val context: Context?, val data: List<RecordList>) : RecyclerView.Adapter<RecordAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_record, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            price.text = data[position].money
            multiple.text = data[position].multiple
            createTime.text = data[position].createtime
            unit.text = data[position].id
            num.text = data[position].number
            ifOpen.text = if (data[position].thigh == "1") "已开奖" else "未开奖"
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}
