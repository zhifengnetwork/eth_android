package com.zf.eth.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zf.eth.R
import kotlinx.android.synthetic.main.item_team.view.*

class TeamAdapter(val context: Context) : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_team, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = 4

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            setOnClickListener {
                holder.itemView.isSelected = !holder.itemView.isSelected
                if (holder.itemView.isSelected) {
                    phone.visibility = View.VISIBLE
                } else {
                    phone.visibility = View.GONE
                }

            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}