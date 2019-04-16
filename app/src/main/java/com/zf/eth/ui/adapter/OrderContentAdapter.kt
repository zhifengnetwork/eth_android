package com.zf.eth.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zf.eth.R

class OrderContentAdapter(val context:Context?):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_c2c_order, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = 4

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}