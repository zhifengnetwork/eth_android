package com.zf.eth.view.dialog

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.zf.eth.R
import com.zf.eth.utils.DensityUtil
import com.zf.eth.utils.KeyBordUitls
import kotlinx.android.synthetic.main.dialog_multiple.view.*


class MultipleDialog : DialogFragment() {


    companion object {

        var mNum: Int = 1
        fun showDialog(fragmentManager: FragmentManager, num: Int): MultipleDialog {
            val receiveDialog = MultipleDialog()

            mNum = num

            receiveDialog.show(fragmentManager, "")
            //点击空白处是否关闭dialog
            receiveDialog.isCancelable = false
            return receiveDialog
        }

    }

    var onNumListener: ((Int) -> Unit)? = null


    override fun onStart() {
        super.onStart()
        val window = dialog?.window
        val sp = window?.attributes
        sp?.width = DensityUtil.dp2px(250f)
        sp?.height = DensityUtil.dp2px(180f)
        sp?.dimAmount = 0.3f
        window?.attributes = sp
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val view = LayoutInflater.from(activity).inflate(R.layout.dialog_multiple, container, false)
        view.apply {

            input.setText(mNum.toString())

            input.setSelection(input.text.length)

            cancel.setOnClickListener {
                dismiss()
                KeyBordUitls.closeKeybord(input, context)
            }

            confirm.setOnClickListener {
                if (input.text.isNotEmpty() && input.text.toString().toInt() >= 0) {
                    KeyBordUitls.closeKeybord(input, context)
                    dismiss()
                    onNumListener?.invoke(input.text.toString().toInt())
                }

            }

        }

        return view
    }

}
