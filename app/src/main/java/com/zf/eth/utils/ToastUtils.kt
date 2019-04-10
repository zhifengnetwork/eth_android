package com.zf.eth.utils

import android.content.Context
import android.widget.Toast


object ToastUtils {

    private var isShow = true
    private var mToast: Toast? = null


    fun controlShow(isShowToast: Boolean) {
        isShow = isShowToast
    }

    fun cancelToast() {
        if (isShow && mToast != null) {
            mToast!!.cancel()
        }
    }

    fun showShort(context: Context, message: CharSequence) {
        if (isShow) {
            if (mToast == null) {
                mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
            } else {
                mToast?.setText(message)
            }
            mToast?.show()
        }
    }

    fun showLong(context: Context, message: CharSequence) {
        if (isShow) {
            if (mToast == null) {
                mToast = Toast.makeText(context, message, Toast.LENGTH_LONG)
            } else {
                mToast?.setText(message)
            }
            mToast?.show()
        }
    }


}