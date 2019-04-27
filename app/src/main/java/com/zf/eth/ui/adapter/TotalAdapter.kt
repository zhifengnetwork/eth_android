package com.zf.eth.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zf.eth.R
import com.zf.eth.mvp.bean.InvestList
import kotlinx.android.synthetic.main.item_c2c_record.view.*
import kotlinx.android.synthetic.main.item_invest_2.view.*
import kotlinx.android.synthetic.main.item_transfer_2.view.*
import kotlinx.android.synthetic.main.item_withdraw_2.view.*


/**
 * 总记录
 */
class TotalAdapter(val context: Context?, val data: List<InvestList>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TOUZI = 0
    private val TIXIAN = 1
    private val ZHUANBI = 2
    private val C2C = 3

    override fun getItemViewType(position: Int): Int {
        return when (data[position].type) {
            "1" -> TOUZI
            "3" -> ZHUANBI
            "4" -> TIXIAN
            "5" -> C2C
            else -> TOUZI
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TOUZI -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_invest_2, parent, false)
                TouZiHolder(view)
            }
            ZHUANBI -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_transfer_2, parent, false)
                ZhuanBiHolder(view)
            }
            TIXIAN -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_withdraw_2, parent, false)
                TiXianHolder(view)
            }
            C2C -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_c2c_record, parent, false)
                C2CHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_total, parent, false)
                TouZiHolder(view)
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is TouZiHolder -> {
                holder.itemView.apply {
                    money1.text = data[position].money
                    time1.text = data[position].createtime
                    title1.text = data[position].title
                    balance1.text = data[position].after_money
                    payType1.text = if (data[position].payment == "1") "自由账户可用余额:" else "复投账户可用余额:"
                }
            }
            is TiXianHolder -> {
                holder.itemView.apply {
                    money2.text = data[position].money
                    time2.text = data[position].createtime
                    title2.text = data[position].title
                    truePrice2.text = data[position].realmoney
                    charge2.text = data[position].charge
                    freeMoney2.text = data[position].after_money
                    payType2.text = if (data[position].payment == "1") "自由账户可用余额:" else "复投账户可用余额:"
                }
            }
            is ZhuanBiHolder -> {
                holder.itemView.apply {
                    time4.text = data[position].createtime
                    name4.text = data[position].openid2
                    price4.text = "${data[position].money}个"
                    serviceMoney4.text = "${data[position].money2}个"
                    title4.text = data[position].title
                    fromName4.text = data[position].openid
                }
            }
            is C2CHolder -> {
                holder.itemView.apply {
                    title3.text = data[position].title
                    time3.text = data[position].createtime
                    price3.text = data[position].RMB
                }
            }
        }

    }

    class TouZiHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    class TiXianHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    class ZhuanBiHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    class C2CHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}