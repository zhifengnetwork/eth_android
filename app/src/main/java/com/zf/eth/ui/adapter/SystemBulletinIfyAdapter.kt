package com.zf.eth.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zf.eth.R
import com.zf.eth.api.UriConstant
import com.zf.eth.mvp.bean.Articles
import com.zf.eth.ui.activity.NoticeContentActivity
import com.zf.eth.utils.GlideUtils
import kotlinx.android.synthetic.main.layout_bullentin_item.view.*

class SystemBulletinIfyAdapter(val context: Context?, private val mData: ArrayList<Articles>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_bullentin_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.apply {
            //图片没添加
            item_name.text = mData[position].article_title
            GlideUtils.loadUrlImage(context, UriConstant.BASE_IMG_URL + mData[position].resp_img, item_img)
            setOnClickListener {
                NoticeContentActivity.actionStart(context,mData[position].resp_desc)
            }
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}