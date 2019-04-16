package com.zf.eth.ui.fragment

import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.zf.eth.R
import com.zf.eth.base.BaseFragment
import com.zf.eth.mvp.bean.RankBean
import com.zf.eth.ui.adapter.RankAdapter
import com.zf.eth.utils.RecyclerViewDivider
import kotlinx.android.synthetic.main.fragment_rank.*

class RankFragment : BaseFragment() {

    private var mType = ""
    private var mBean: RankBean? = null

    companion object {

        const val YESTERDAY = "YESTERDAY"
        const val TODAY = "TODAY"
        const val LIST = "LIST"

        fun getInstance(type: String, bean: RankBean): RankFragment {
            val fragment = RankFragment()
            fragment.mType = type
            fragment.mBean = bean
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_rank


    private val adapter by lazy { RankAdapter(context, mType, mBean) }

    override fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(RecyclerViewDivider(context, LinearLayout.VERTICAL,
                1, ContextCompat.getColor(context!!, R.color.colorBackground)))
    }

    override fun lazyLoad() {

    }

    override fun initEvent() {
    }
}