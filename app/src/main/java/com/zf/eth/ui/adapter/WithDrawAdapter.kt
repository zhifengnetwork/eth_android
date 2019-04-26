package com.zf.eth.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zf.eth.R
import com.zf.eth.mvp.bean.InvestList
import kotlinx.android.synthetic.main.item_withdraw.view.*

class WithDrawAdapter(val context: Context?, val data: List<InvestList>) :
    RecyclerView.Adapter<WithDrawAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_withdraw, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.apply {
            money2.text = data[position].money
            time2.text = data[position].createtime
            title2.text = data[position].title
            truePrice2.text = data[position].realmoney
            charge2.text = data[position].charge
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}