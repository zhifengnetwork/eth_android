package com.zf.eth.ui.fragment.game

import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.zf.eth.R
import com.zf.eth.base.BaseFragment
import com.zf.eth.mvp.bean.PourBean
import com.zf.eth.showToast
import com.zf.eth.ui.adapter.PourAdapter
import com.zf.eth.view.dialog.MultipleDialog
import kotlinx.android.synthetic.main.fragment_pour.*
import kotlinx.android.synthetic.main.layout_num_decade.*
import kotlinx.android.synthetic.main.layout_num_hundred.*
import kotlinx.android.synthetic.main.layout_num_single.*

/**
 * 3D下注
 */
class PourFragment : BaseFragment() {

    companion object {
        fun getInstance(): PourFragment {
            return PourFragment()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_pour

    private val data = ArrayList<PourBean>()
    private val adapter by lazy { PourAdapter(context, data) }

    private val map = HashMap<Int, Int>()


    override fun initView() {

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        repeat(hundredLayout.size) { index ->
            hundredLayout[index].setOnClickListener {
                repeat(hundredLayout.size) { pos -> hundredLayout[pos].isSelected = false }
                hundredLayout[index].isSelected = true
                map[0] = index
                initChoose()
            }
        }

        repeat(decadeLayout.size) { index ->
            decadeLayout[index].setOnClickListener {
                repeat(decadeLayout.size) { pos -> decadeLayout[pos].isSelected = false }
                decadeLayout[index].isSelected = true
                map[1] = index
                initChoose()
            }
        }

        repeat(singleLayout.size) { index ->
            singleLayout[index].setOnClickListener {
                repeat(singleLayout.size) { pos -> singleLayout[pos].isSelected = false }
                singleLayout[index].isSelected = true
                map[2] = index
                initChoose()
            }
        }

    }

    private fun initChoose() {

        if (map.size == 3) {
            data.add(PourBean(map[0], map[1], map[2], 1))
            adapter.notifyDataSetChanged()
            map.clear()
            repeat(singleLayout.size) { pos -> singleLayout[pos].isSelected = false }
            repeat(decadeLayout.size) { pos -> decadeLayout[pos].isSelected = false }
            repeat(hundredLayout.size) { pos -> hundredLayout[pos].isSelected = false }
        }

    }

    override fun lazyLoad() {
    }

    override fun initEvent() {

        confirm.setOnClickListener {
            if (from.text.isEmpty() || to.text.isEmpty()) {
                showToast("请输入包号区间")
            } else if (multiple.text.isEmpty()) {
                showToast("请输入包号倍数")
            } else if (from.text.toString().toInt() >= to.text.toString().toInt()) {
                showToast("请输入从小到大的0-9区间")
            } else {
                //满足条件
                if (!confirm.isSelected) {
                    confirm.isSelected = true
                    cancel.isSelected = true
                    val fromNum = from.text.toString().toInt()
                    val toNum = to.text.toString().toInt()
                    data.clear()
                    for (i in fromNum..toNum) {
                        for (j in fromNum..toNum) {
                            for (k in fromNum..toNum) {
                                val rb = PourBean(i, j, k)
                                rb.multiple = multiple.text.toString().toInt()
                                data.add(rb)
                            }
                        }
                    }
                    adapter.notifyDataSetChanged()
                }
            }

            cancel.setOnClickListener {
                if (cancel.isSelected) {
                    confirm.isSelected = false
                    cancel.isSelected = false
                    data.clear()
                    adapter.notifyDataSetChanged()
                }
            }
        }

        adapter.deleteListener = {
            data.removeAt(it)
            adapter.notifyDataSetChanged()
        }

        adapter.multipleListener = { index ->
            MultipleDialog.showDialog(childFragmentManager, data[index].multiple).onNumListener = {
                data[index].multiple = it
                adapter.notifyDataSetChanged()
            }
        }

    }

    private val hundredLayout: List<ImageView> by lazy {
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
    }

    private val decadeLayout: List<ImageView> by lazy {
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
    }

    private val singleLayout: List<ImageView> by lazy {
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
    }
}