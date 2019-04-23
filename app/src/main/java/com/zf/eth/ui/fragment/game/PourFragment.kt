package com.zf.eth.ui.fragment.game

import android.app.Activity
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.zf.eth.R
import com.zf.eth.base.BaseFragment
import com.zf.eth.mvp.bean.BetBean
import com.zf.eth.mvp.bean.GameHomeBean
import com.zf.eth.mvp.bean.LotteryNumber
import com.zf.eth.mvp.bean.PourNumBean
import com.zf.eth.mvp.contract.BetContract
import com.zf.eth.mvp.presenter.BetPresenter
import com.zf.eth.showToast
import com.zf.eth.ui.activity.GameRulesActivity
import com.zf.eth.ui.activity.RankActivity
import com.zf.eth.ui.adapter.PourAdapter
import com.zf.eth.utils.DensityUtil
import com.zf.eth.utils.RecyclerViewDivider
import com.zf.eth.view.LayoutGravity
import com.zf.eth.view.dialog.GameBuyDialog
import com.zf.eth.view.dialog.MultipleDialog
import com.zf.eth.view.popwindow.DrawWinPopupWindow
import kotlinx.android.synthetic.main.fragment_pour.*
import java.math.BigDecimal

/**
 * 3D下注
 */
class PourFragment : BaseFragment(), BetContract.View {

    private var lotteryData = listOf<LotteryNumber>()

    //开奖号码，每注金额
    override fun setGameHome(bean: GameHomeBean) {
        bean.sale1?.let {
            lotteryData = it
        }
        unitPrice.text = bean.price
        if (bean.sale1?.get(0) != null) {
            lotteryTime.text = bean.sale1[0].time
            lotteryNum.text = bean.sale1[0].number
        }
    }

    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
    }

    /** 第二部，确认下注  */
    override fun setConfirmBeat(msg: String) {
        showToast(msg)
    }

    /** 第一步，确认信息 */
    override fun setBet(bean: BetBean) {
        //适配器的列表
        val list = adapter.pourData
        //传递给后台的参数
        val data: Array<Array<String>> = Array(list.size) { arrayOf("", "") }

        var num = 0
        val jsonList = ArrayList<PourNumBean>()
        repeat(list.size) {
            //求总共多少注
            num += list[it].multiple
            jsonList.add(
                PourNumBean(
                    list[it].hundred.toString() + list[it].decade.toString() + list[it].single.toString(),
                    list[it].multiple.toString()
                )
            )
//            data[it] = arrayOf(
//                list[it].hundred.toString() + list[it].decade.toString() + list[it].single.toString(),
//                list[it].multiple.toString()
//            )
        }

        val finalJson = Gson().toJson(jsonList)

        //求下注金额（下注总数*单价）
        val price = (BigDecimal(num.toString()).multiply(BigDecimal(unitPrice.text.toString()))).toString()
        GameBuyDialog.showDialog(childFragmentManager, bean, num.toString(), price).onConfirmListener = { payType ->
            betPresenter.requestConfirmBet(2, payType, "1", finalJson)
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
        betPresenter.requestGameHome()
    }

    override fun initEvent() {

        rankLayout.setOnClickListener { RankActivity.actionStart(context) }

        //游戏规则
        gameRules.setOnClickListener { GameRulesActivity.actionStart(context) }

        //确定
        confirm.setOnClickListener {
            if (adapter.pourData.isEmpty()) {
                showToast("请先下注")
            } else {
                //确认信息获取账户余额
                betPresenter.requestBet(1, null, "", null)
            }
        }

        //开奖号
        drawLayout.setOnClickListener {
            val popupWindow = object : DrawWinPopupWindow(
                activity as Activity,
                R.layout.pop_draw_win, DensityUtil.dp2px(120f),
                DensityUtil.dp2px(150f),
                lotteryData
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