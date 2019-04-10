package com.zf.eth.ui.fragment.game

import androidx.recyclerview.widget.LinearLayoutManager
import com.zf.eth.R
import com.zf.eth.base.BaseFragment
import com.zf.eth.ui.adapter.PourAdapter
import com.zf.eth.view.dialog.MultipleDialog
import kotlinx.android.synthetic.main.fragment_pour.*

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

    private val adapter by lazy { PourAdapter(context) }

    override fun initView() {

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

    }

    override fun lazyLoad() {
    }

    override fun initEvent() {

        adapter.multipleListener = { bean ->
            MultipleDialog.showDialog(childFragmentManager, bean.num).onNumListener = {
                adapter.updateMultiple(bean.position, it)

            }
        }

    }

}