package com.zf.eth.ui.fragment.game

import android.app.Activity
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.zf.eth.R
import com.zf.eth.base.BaseFragment
import com.zf.eth.mvp.bean.BetBean
import com.zf.eth.mvp.contract.BetContract
import com.zf.eth.mvp.presenter.BetPresenter
import com.zf.eth.ui.activity.GameRulesActivity
import com.zf.eth.ui.adapter.PourAdapter
import com.zf.eth.utils.DensityUtil
import com.zf.eth.utils.RecyclerViewDivider
import com.zf.eth.view.LayoutGravity
import com.zf.eth.view.dialog.GameBuyDialog
import com.zf.eth.view.dialog.MultipleDialog
import com.zf.eth.view.popwindow.DrawWinPopupWindow
import kotlinx.android.synthetic.main.fragment_pour.*

/**
 * 3D下注
 */
class PourFragment : BaseFragment(), BetContract.View {

    override fun showError(msg: String, errorCode: Int) {
    }

    override fun setBet(bean: BetBean) {

        //适配器的列表
        val list = adapter.pourData
        //传递给后台的参数
        val data: Array<Array<String>> = Array(list.size) { arrayOf("", "") }
        repeat(list.size) {
            data[it] = arrayOf(
                list[it].hundred.toString() + list[it].decade.toString() + list[it].single.toString(),
                list[it].multiple.toString()
            )
        }

        GameBuyDialog.showDialog(childFragmentManager, bean).onConfirmListener = { payType ->
            betPresenter.requestBet(2, payType, "1", data)
        }
    }

    override fun showLoading() {
        showLoadingDialog()
    }

    override fun dismissLoading() {
        dismissLoadingDialog()
    }

    companion object {
        fun getInstance(): PourFragment {
            return PourFragment()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_pour

    private val adapter by lazy { PourAdapter(context) }

    private val betPresenter by lazy { BetPresenter() }

    override fun initView() {
        betPresenter.attachView(this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
            RecyclerViewDivider(
                context, LinearLayout.VERTICAL,
                1, ContextCompat.getColor(context!!, R.color.color_game_item)
            )
        )
    }

    override fun lazyLoad() {
    }

    override fun initEvent() {

        //游戏规则
        gameRules.setOnClickListener { GameRulesActivity.actionStart(context) }

        //确定
        confirm.setOnClickListener {
            //确认信息获取账户余额
            betPresenter.requestBet(1, null, "", null)
        }

        //开奖号
        drawLayout.setOnClickListener {
            val popupWindow = object : DrawWinPopupWindow(
                activity as Activity,
                R.layout.pop_draw_win, DensityUtil.dp2px(120f),
                DensityUtil.dp2px(150f)
            ) {}
            val layoutGravity = LayoutGravity(LayoutGravity.ALIGN_RIGHT)
            popupWindow.showBashOfAnchor(drawLayout, layoutGravity, 0, 0)
        }

        adapter.multipleListener = { bean ->
            MultipleDialog.showDialog(childFragmentManager, bean.num).onNumListener = {
                adapter.updateMultiple(bean.position, it)
            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        betPresenter.detachView()
    }

}