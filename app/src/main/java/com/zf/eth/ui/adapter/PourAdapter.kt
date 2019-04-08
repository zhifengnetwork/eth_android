package com.zf.eth.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zf.eth.R
import com.zf.eth.mvp.bean.PourBean
import kotlinx.android.synthetic.main.item_pour.view.*

class PourAdapter(val context: Context?, private val data: List<PourBean>) :
    RecyclerView.Adapter<PourAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_pour, parent, false)
        return ViewHolder(view)
    }

    var deleteListener: ((Int) -> Unit)? = null
    var multipleListener: ((Int) -> Unit)? = null

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {

            hundred.text = data[position].hundred.toString()
            decade.text = data[position].decade.toString()
            single.text = data[position].single.toString()
            multiPle.text = data[position].multiple.toString()

            multiPle.setOnClickListener {
                multipleListener?.invoke(position)
            }

            delete.setOnClickListener {
                deleteListener?.invoke(position)
            }
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
