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
import com.zf.eth.api.UriConstant
import com.zf.eth.utils.DensityUtil
import com.zf.eth.utils.GlideUtils
import kotlinx.android.synthetic.main.dialog_contact.view.*


class ContactDialog : DialogFragment() {

    companion object {

        var qqImg = ""
        var wxImg = ""
        fun showDialog(fragmentManager: FragmentManager, qq: String?, wx: String?): ContactDialog {
            val receiveDialog = ContactDialog()

            qq?.let { qqImg = it }
            wx?.let { wxImg = it }



            receiveDialog.show(fragmentManager, "")
            //点击空白处是否关闭dialog
            receiveDialog.isCancelable = false
            return receiveDialog
        }

    }

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

        val view = LayoutInflater.from(activity).inflate(R.layout.dialog_contact, container, false)
        view.apply {

            qqBtn.isChecked = true
            GlideUtils.loadUrlImage(context, UriConstant.BASE_IMG_URL + qqImg, imageView)

            radioGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    qqBtn.id -> {
                        GlideUtils.loadUrlImage(context, UriConstant.BASE_IMG_URL + qqImg, imageView)
                    }
                    wxBtn.id -> {
                        GlideUtils.loadUrlImage(context, UriConstant.BASE_IMG_URL + wxImg, imageView)
                    }
                }
            }

            cancel.setOnClickListener {
                dismiss()
            }
        }

        return view
    }

}
