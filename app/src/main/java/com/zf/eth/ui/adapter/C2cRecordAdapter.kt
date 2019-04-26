package com.zf.eth.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zf.eth.R
import com.zf.eth.mvp.bean.InvestList
import kotlinx.android.synthetic.main.item_c2c_record.view.*

class C2CRecordAdapter(val context: Context?, val data: List<InvestList>) :
        RecyclerView.Adapter<C2CRecordAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_c2c_record, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            title.text = data[position].title
            time.text = data[position].createtime
            price.text = data[position].RMB
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}