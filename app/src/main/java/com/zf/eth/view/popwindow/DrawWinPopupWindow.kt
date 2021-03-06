package com.zf.eth.view.popwindow

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.PopupWindow
import androidx.recyclerview.widget.LinearLayoutManager
import com.zf.eth.mvp.bean.LotteryNumber
import com.zf.eth.ui.adapter.PopDrawAdapter
import com.zf.eth.view.LayoutGravity
import kotlinx.android.synthetic.main.pop_draw_win.view.*

/**
 * 开奖号
 */
abstract class DrawWinPopupWindow(var context: Activity, layoutRes: Int, w: Int, h: Int,data:List<LotteryNumber>) {
    private val contentView: View
    private val popupWindow: PopupWindow
    private var isShowing = false

    private val mData = data

    init {

        contentView = LayoutInflater.from(context).inflate(layoutRes, null, false)
        initView()
        popupWindow = PopupWindow(contentView, w, h, true)
        initWindow()
    }

    private var mListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    interface OnItemClickListener {
        fun onBack(address: String)
    }

    private fun initView() {
        contentView.apply {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = PopDrawAdapter(context ,mData)

        }
    }

    private fun initWindow() {
        popupWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        popupWindow.isOutsideTouchable = true
        popupWindow.isTouchable = true
        /** 设置出入动画  */
//        popupWindow.animationStyle = R.style.pop_translate
    }


    fun showBashOfAnchor(anchor: View, layoutGravity: LayoutGravity, xmerge: Int, ymerge: Int) {
        val offset = layoutGravity.getOffset(anchor, popupWindow)
        popupWindow.showAsDropDown(anchor, offset[0] + xmerge, offset[1] + ymerge)
        isShowing = true

        popupWindow.setOnDismissListener {
            //隐藏后显示背景为透明
            val lp = context.window.attributes
            lp.alpha = 1.0f
            context.window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            context.window.attributes = lp
        }

        //显示时候设置背景为灰色
        val lp = context.window.attributes
        lp.alpha = 1.0f
        context.window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        context.window.attributes = lp
    }

    fun showAtLocation(parent: View, gravity: Int, x: Int, y: Int) {
        popupWindow.showAtLocation(parent, gravity, x, y)
        isShowing = true
        popupWindow.setOnDismissListener {
            //隐藏后显示背景为透明
            val lp = context.window.attributes
            lp.alpha = 1.0f
            context.window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            context.window.attributes = lp
        }

        //显示时候设置背景为灰色
        val lp = context.window.attributes
        lp.alpha = 0.7f
        context.window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        context.window.attributes = lp
    }

    fun onDismiss() {
        popupWindow.dismiss()
        isShowing = false

    }

}