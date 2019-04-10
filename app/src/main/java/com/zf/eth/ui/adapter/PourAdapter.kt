package com.zf.eth.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.zf.eth.R
import com.zf.eth.mvp.bean.MultipleBean
import com.zf.eth.mvp.bean.PourBean
import com.zf.eth.utils.KeyBordUitls
import com.zf.eth.utils.ToastUtils
import kotlinx.android.synthetic.main.item_pour.view.*
import kotlinx.android.synthetic.main.layout_num_decade.view.*
import kotlinx.android.synthetic.main.layout_num_hundred.view.*
import kotlinx.android.synthetic.main.layout_num_single.view.*
import kotlinx.android.synthetic.main.layout_pour_head.view.*

class PourAdapter(val context: Context?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == typeHead) {
            val view = LayoutInflater.from(context).inflate(R.layout.layout_pour_head, parent, false)
            return HeadHolder(view)
        } else {
            val view = LayoutInflater.from(context).inflate(R.layout.item_pour, parent, false)
            return ContentHolder(view)
        }
    }

    //    var deleteListener: ((Int) -> Unit)? = null
    var multipleListener: ((MultipleBean) -> Unit)? = null

    override fun getItemCount(): Int = pourData.size + 1

    private val pourData = ArrayList<PourBean>()

    private val typeHead = 0
    private val typeContent = 1

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) typeHead else typeContent
    }

    fun updateMultiple(position: Int, input: Int) {
        pourData[position].multiple = input
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is HeadHolder) {

            holder.itemView.apply {

                val map = HashMap<Int, Int>()

                val hundredLayout: List<ImageView> =
                    listOf(
                        num0,
                        num1,
                        num2,
                        num3,
                        num4,
                        num5,
                        num6,
                        num7,
                        num8,
                        num9
                    )


                val decadeLayout: List<ImageView> =
                    listOf(
                        num0_d,
                        num1_d,
                        num2_d,
                        num3_d,
                        num4_d,
                        num5_d,
                        num6_d,
                        num7_d,
                        num8_d,
                        num9_d
                    )

                val singleLayout: List<ImageView> =
                    listOf(
                        num0_s,
                        num1_s,
                        num2_s,
                        num3_s,
                        num4_s,
                        num5_s,
                        num6_s,
                        num7_s,
                        num8_s,
                        num9_s
                    )



                repeat(hundredLayout.size) { index ->
                    hundredLayout[index].setOnClickListener {
                        repeat(hundredLayout.size) { pos -> hundredLayout[pos].isSelected = false }
                        hundredLayout[index].isSelected = true
                        map[0] = index
//                        initChoose()
                        if (map.size == 3) {
                            pourData.add(PourBean(map[0], map[1], map[2], 1))
                            notifyDataSetChanged()
                            map.clear()
                            repeat(singleLayout.size) { pos -> singleLayout[pos].isSelected = false }
                            repeat(decadeLayout.size) { pos -> decadeLayout[pos].isSelected = false }
                            repeat(hundredLayout.size) { pos -> hundredLayout[pos].isSelected = false }
                        }
                    }
                }

                repeat(decadeLayout.size) { index ->
                    decadeLayout[index].setOnClickListener {
                        repeat(decadeLayout.size) { pos -> decadeLayout[pos].isSelected = false }
                        decadeLayout[index].isSelected = true
                        map[1] = index
//                        initChoose()
                        if (map.size == 3) {
                            pourData.add(PourBean(map[0], map[1], map[2], 1))
                            notifyDataSetChanged()
                            map.clear()
                            repeat(singleLayout.size) { pos -> singleLayout[pos].isSelected = false }
                            repeat(decadeLayout.size) { pos -> decadeLayout[pos].isSelected = false }
                            repeat(hundredLayout.size) { pos -> hundredLayout[pos].isSelected = false }
                        }
                    }
                }

                repeat(singleLayout.size) { index ->
                    singleLayout[index].setOnClickListener {
                        repeat(singleLayout.size) { pos -> singleLayout[pos].isSelected = false }
                        singleLayout[index].isSelected = true
                        map[2] = index
//                        initChoose()
                        if (map.size == 3) {
                            pourData.add(PourBean(map[0], map[1], map[2], 1))
                            notifyDataSetChanged()
                            map.clear()
                            repeat(singleLayout.size) { pos -> singleLayout[pos].isSelected = false }
                            repeat(decadeLayout.size) { pos -> decadeLayout[pos].isSelected = false }
                            repeat(hundredLayout.size) { pos -> hundredLayout[pos].isSelected = false }
                        }
                    }
                }

                confirm.setOnClickListener {

                    KeyBordUitls.closeKeybord(from, context)
                    if (from.text.isEmpty() || to.text.isEmpty()) {
                        ToastUtils.showShort(context, "请输入包号区间")
                    } else if (multiple.text.isEmpty()) {
                        ToastUtils.showShort(context, "请输入包号倍数")
                    } else if (from.text.toString().toInt() >= to.text.toString().toInt()) {
                        ToastUtils.showShort(context, "请输入从小到大的0-9区间")
                    } else if (multiple.text.toString().toInt() < 1) {
                        ToastUtils.showShort(context, "包号倍数不能小于1")
                    } else {
                        //满足条件
                        if (!confirm.isSelected) {
                            confirm.isSelected = true
                            cancel.isSelected = true
                            val fromNum = from.text.toString().toInt()
                            val toNum = to.text.toString().toInt()
                            pourData.clear()
                            for (i in fromNum..toNum) {
                                for (j in fromNum..toNum) {
                                    for (k in fromNum..toNum) {
                                        val rb = PourBean(i, j, k)
                                        rb.multiple = multiple.text.toString().toInt()
                                        pourData.add(rb)
                                    }
                                }
                            }
                            notifyDataSetChanged()
                        }
                    }

                }

                cancel.setOnClickListener {
                    KeyBordUitls.closeKeybord(from, context)
                    if (cancel.isSelected) {
                        confirm.isSelected = false
                        cancel.isSelected = false
                        pourData.clear()
                        notifyDataSetChanged()
                    }
                }
            }

        } else {
            holder.itemView.apply {

                hundred.text = pourData[position - 1].hundred.toString()
                decade.text = pourData[position - 1].decade.toString()
                single.text = pourData[position - 1].single.toString()
                multiPle.text = pourData[position - 1].multiple.toString()

                multiPle.setOnClickListener {
                    multipleListener?.invoke(MultipleBean(position - 1, pourData[position - 1].multiple))
                }

                delete.setOnClickListener {
                    pourData.removeAt(position - 1)
                    notifyDataSetChanged()
                }

            }
        }

    }


    class HeadHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ContentHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
