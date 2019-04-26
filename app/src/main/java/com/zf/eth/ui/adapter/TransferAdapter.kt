package com.zf.eth.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zf.eth.R
import com.zf.eth.mvp.bean.InvestList
import kotlinx.android.synthetic.main.item_transfer.view.*

/**
 * 转币记录
 */
class TransferAdapter(val context: Context?, val data: List<InvestList>) :
    RecyclerView.Adapter<TransferAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_transfer, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.apply {
            time4.text = data[position].createtime
            name4.text = data[position].nickname
            price4.text = "${data[position].money}个"
            serviceMoney4.text = "${data[position].money2}个"
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}