package com.zf.eth.ui.fragment.withdraw

import android.view.View
import androidx.core.widget.addTextChangedListener
import com.zf.eth.R
import com.zf.eth.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_eth.*

/**
 * ETH账户提现
 */
class ETHFragment : BaseFragment() {

    companion object {
        fun getInstance(): ETHFragment {
            return ETHFragment()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_eth

    override fun initView() {
        dashLine1.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
    }

    override fun lazyLoad() {
    }

    override fun initEvent() {
        input.addTextChangedListener {
            if (input.text.isEmpty()) {
                //手续费布局
                chargeLayout.visibility = View.GONE
                //实际到账布局
                arrivalLayout.visibility = View.GONE
            } else {
                chargeLayout.visibility = View.VISIBLE
                arrivalLayout.visibility = View.VISIBLE
            }
        }
    }
}