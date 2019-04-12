package com.zf.eth.view.dialog

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.zf.eth.R
import com.zf.eth.mvp.bean.BetBean
import com.zf.eth.utils.DensityUtil
import com.zf.eth.utils.ToastUtils
import kotlinx.android.synthetic.main.dialog_game_buy.view.*


class GameBuyDialog : DialogFragment() {


    companion object {

        private var mBean: BetBean? = null
        fun showDialog(fragmentManager: FragmentManager, bean: BetBean): GameBuyDialog {
            val receiveDialog = GameBuyDialog()

            mBean = bean

            receiveDialog.show(fragmentManager, "")
            //点击空白处是否关闭dialog
            receiveDialog.isCancelable = false
            return receiveDialog
        }

    }

    var onConfirmListener: ((type: Int) -> Unit)? = null


    override fun onStart() {
        super.onStart()
        val window = dialog?.window
        val sp = window?.attributes
        sp?.width = DensityUtil.dp2px(250f)
        sp?.height = LinearLayout.LayoutParams.WRAP_CONTENT
        sp?.dimAmount = 0.3f
        window?.attributes = sp
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val view = LayoutInflater.from(activity).inflate(R.layout.dialog_game_buy, container, false)
        view.apply {


            freeMoney.text = mBean?.list?.credit2
            reMoney.text = mBean?.list?.credit4

            payType.setOnClickListener {
                payType.isSelected = !payType.isSelected
                payLayout.visibility = if (payType.isSelected) View.VISIBLE else View.GONE
            }

            freeAcctount.setOnClickListener {
                payType.isSelected = !payType.isSelected
                payType.text = freeAcctount.text
                freeAcctount.isSelected = true
                reAccount.isSelected = false
                payLayout.visibility = View.GONE
            }

            reAccount.setOnClickListener {
                payType.isSelected = !payType.isSelected
                payType.text = reAccount.text
                reAccount.isSelected = true
                freeAcctount.isSelected = false
                payLayout.visibility = View.GONE
            }

            confirm.setOnClickListener {
                dismiss()
                /** 判断支付方式 */
                when {
                    reAccount.isSelected -> onConfirmListener?.invoke(2)
                    freeAcctount.isSelected -> onConfirmListener?.invoke(1)
                    else -> ToastUtils.showShort(context, "请选择支付方式")
                }
            }

            cancel.setOnClickListener {
                dismiss()
            }

        }

        return view
    }

}
