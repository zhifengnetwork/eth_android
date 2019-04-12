package com.zf.eth.ui.adapter.earn

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zf.eth.R
import com.zf.eth.mvp.bean.EarnList
import kotlinx.android.synthetic.main.item_earn_invest.view.*

/**
 * 投资收益
 */
class InvestAdapter(val context: Context?, val data: List<EarnList>) : RecyclerView.Adapter<InvestAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_earn_invest, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            time.text = data[position].createtime
            reMoney.text = data[position].money2
            freeMoney.text = data[position].money
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}