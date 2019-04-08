package com.zf.eth.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zf.eth.R

class InvestAdapter(val context: Context) : RecyclerView.Adapter<InvestAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_invest, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int =4

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}