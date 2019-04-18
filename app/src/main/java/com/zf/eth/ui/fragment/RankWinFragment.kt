package com.zf.eth.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.zf.eth.R
import com.zf.eth.base.BaseFragment
import com.zf.eth.mvp.bean.RankBean
import com.zf.eth.ui.adapter.RankWinAdapter
import kotlinx.android.synthetic.main.fragment_rank.*

class RankWinFragment : BaseFragment() {

    private var mBean: RankBean? = null

    companion object {


        fun getInstance(bean: RankBean): RankWinFragment {
            val fragment = RankWinFragment()
            fragment.mBean = bean
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_rank_win

    private val winAdapter by lazy { RankWinAdapter(context, mBean) }

    override fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = winAdapter

    }

    override fun lazyLoad() {

    }

    override fun initEvent() {
    }

}