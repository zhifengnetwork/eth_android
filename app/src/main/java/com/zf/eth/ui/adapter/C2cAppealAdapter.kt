package com.zf.eth.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zf.eth.R
import com.zf.eth.mvp.bean.AppealList
import com.zf.eth.ui.activity.C2cAppealActivity
import kotlinx.android.synthetic.main.item_c2c_appeal.view.*

class C2cAppealAdapter(val context: Context?,val data:List<AppealList>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val mData=data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_c2c_appeal, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.apply {
            //申述原因
            appeal_name.text=mData[position].text
            //申述时间
            appeal_time.text=mData[position].createtime
        }
        //点击事件传递申诉的ID
       holder.itemView.setOnClickListener {
           C2cAppealActivity.actionStart(context,mData[position].id)
       }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}