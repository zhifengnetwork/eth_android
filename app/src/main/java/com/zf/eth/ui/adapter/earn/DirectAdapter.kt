package com.zf.eth.ui.adapter.earn

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zf.eth.R
import com.zf.eth.mvp.bean.EarnList
import kotlinx.android.synthetic.main.item_earn_direct.view.*

/**
 * 直推奖
 */
class DirectAdapter(val context: Context?, val data: List<EarnList>) : RecyclerView.Adapter<DirectAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_earn_direct, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            time.text = data[position].createtime
            moneyFrom.text = data[position].nickname
            freeMoney.text = data[position].money
//            reMoney.text = data[position].money2

        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}