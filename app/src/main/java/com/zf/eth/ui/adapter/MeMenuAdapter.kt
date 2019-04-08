package com.zf.eth.ui.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView
import com.zf.eth.R
import kotlinx.android.synthetic.main.layout_me_item.view.*

class MeMenuAdapter(val context:Context?,val list:List<String>,val ico:List<Int>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
     val titles=list
    val img= ico
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_me_item, parent, false)
        return  oneViewHolder(view)
    }

    override fun getItemCount(): Int =titles.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.apply {
            me_item_img.setImageResource(img[position])
            me_item_name.text=titles[position]
        }


    }
    class oneViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}


