package com.zf.eth.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zf.eth.R
import com.zf.eth.mvp.bean.Articles
import kotlinx.android.synthetic.main.layout_bullentin_item.view.*

class SystemBulletinIfyAdapter(val context: Context?,val mData:ArrayList<Articles>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_bullentin_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int =mData.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.apply {
            //图片没添加
            item_name.text=mData[position].article_title
        }

    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}