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
import com.zf.eth.utils.DensityUtil
import kotlinx.android.synthetic.main.dialog_log_out.view.*
import java.math.BigDecimal
import java.text.DecimalFormat


class LogOutDialog : DialogFragment() {

    companion object {

        var mAll: String = ""
        fun showDialog(fragmentManager: FragmentManager, all: String): LogOutDialog {
            val receiveDialog = LogOutDialog()

            mAll = all

            receiveDialog.show(fragmentManager, "")
            //点击空白处是否关闭dialog
            receiveDialog.isCancelable = false
            return receiveDialog
        }

    }

    var onConfirmListener: ((String) -> Unit)? = null


    override fun onStart() {
        super.onStart()
        val window = dialog?.window
        val sp = window?.attributes
        sp?.width = DensityUtil.dp2px(280f)
        sp?.height = LinearLayout.LayoutParams.WRAP_CONTENT
        sp?.dimAmount = 0.3f
        window?.attributes = sp
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val view = LayoutInflater.from(activity).inflate(R.layout.dialog_log_out, container, false)
        view.apply {
            allPrice.text = mAll

            val all = DecimalFormat("#.00000").format(mAll.toFloat())

            val refundPrice = (BigDecimal(all).multiply(BigDecimal("0.5"))).toString()

            refund.text = refundPrice

            confirm.setOnClickListener {
                onConfirmListener?.invoke(refundPrice)
                dismiss()
            }
            cancel.setOnClickListener {
                dismiss()
            }
        }

        return view
    }

}
