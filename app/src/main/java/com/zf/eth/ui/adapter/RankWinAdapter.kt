package com.zf.eth.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.zf.eth.R
import com.zf.eth.mvp.bean.RankBean
import kotlinx.android.synthetic.main.item_rank_win.view.*

class RankWinAdapter(val context: Context?, val data: RankBean?) : RecyclerView.Adapter<RankWinAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_rank_win, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data?.winning?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            data?.winning?.let {
                createTime.text = it[position].createtime
                userName.text = it[position].openid
                winNum.text = it[position].number
                price.text = it[position].money
                count.text = it[position].stakesum
                type.text = if (it[position].type == "1") "投注中奖" else "投注排名奖"
                type.setTextColor(
                    ContextCompat.getColor(
                        context, if (it[position].type == "1") R.color.color_red
                        else R.color.color_green
                    )
                )
            }
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}