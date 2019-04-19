package com.zf.eth.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zf.eth.R
import com.zf.eth.mvp.bean.RankBean
import com.zf.eth.ui.fragment.RankFragment
import kotlinx.android.synthetic.main.item_rank_content.view.*

class RankAdapter(val context: Context?, val type: String, val data: RankBean?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val HEADTYPE = 0
    private val CONTENTTYPE = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == CONTENTTYPE) {
            val view = LayoutInflater.from(context).inflate(R.layout.item_rank_content, parent, false)
            ContentHolder(view)
        } else {
            val view = if (type == RankFragment.YESTERDAY) {
                //昨日头布局
                LayoutInflater.from(context).inflate(R.layout.item_rank_head_yes, parent, false)
            } else {
                //今日头布局
                LayoutInflater.from(context).inflate(R.layout.item_rank_head, parent, false)
            }
            HeadHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) HEADTYPE else CONTENTTYPE
    }

    override fun getItemCount(): Int = when {
        data == null -> 1
        type == RankFragment.YESTERDAY -> data.yestoday.size + 1
        type == RankFragment.TODAY -> data.today.size + 1
        type == RankFragment.LIST -> data.winning.size + 1
        else -> 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ContentHolder) {
            holder.itemView.apply {
                when (type) {
                    RankFragment.YESTERDAY -> {
                        data?.yestoday?.let {
                            rank.text = "第${it[position - 1].type}名"
                            userId.text = it[position - 1].id
                            nickName.text = it[position - 1].nickname
                            mayWin.text = "${it[position - 1].yujis}(${it[position - 1].bfb}%)"
                            price.text = it[position - 1].moneys
                        }
                    }
                    RankFragment.TODAY -> {
                        data?.today?.let {
                            rank.text = "第${it[position - 1].type}名"
                            userId.text = it[position - 1].id
                            nickName.text = it[position - 1].nickname
                            mayWin.text = "${it[position - 1].yuji}(${it[position - 1].bfb}%)"
                            price.text = it[position - 1].moneys
                        }
                    }
                }


            }
        }
    }

    class ContentHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class HeadHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}