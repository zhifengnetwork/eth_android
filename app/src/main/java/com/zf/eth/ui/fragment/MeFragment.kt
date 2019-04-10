package com.zf.eth.ui.fragment

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.zf.eth.R
import com.zf.eth.base.BaseFragment
import com.zf.eth.ui.activity.LoginActivity
import com.zf.eth.ui.adapter.MeMenuAdapter
import kotlinx.android.synthetic.main.fragment_me.*

class MeFragment : BaseFragment() {

    companion object {
        fun getInstance(): MeFragment {
            return MeFragment()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_me


    private val title1 = arrayListOf("支付管理", "钱包地址", "C2C订单", "修改密码")
    private val img1 = arrayListOf(
        R.drawable.zhifu,
        R.drawable.qianbao,
        R.drawable.c2c_me,
        R.drawable.xiugaimima,
        R.drawable.yaoqing,
        R.drawable.gonggao,
        R.drawable.keuf,
        R.drawable.touzi,
        R.drawable.appicon3_16
    )
    private val title2 = arrayListOf("我的邀请", "平台公告", "联系客服", "退出机制", "下载APP")
    private val img2 =
        arrayListOf(R.drawable.yaoqing, R.drawable.gonggao, R.drawable.keuf, R.drawable.touzi, R.drawable.appicon3_16)

    override fun initView() {
        //第一个列表菜单
        me_oneRelView.layoutManager = LinearLayoutManager(context)
        val adapter1 = MeMenuAdapter(context, title1, img1)
        me_oneRelView.adapter = adapter1
        //添加自定义颜色分割线
        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        divider.setDrawable(ContextCompat.getDrawable(context!!, R.drawable.shape_custom_divider)!!)

        me_oneRelView.addItemDecoration(divider)

        //第二个列表菜单
        me_twoRelView.layoutManager = LinearLayoutManager(context)
        val adapter2 = MeMenuAdapter(context, title2, img2)
        me_twoRelView.adapter = adapter2
        //添加自定义颜色分割线
        me_twoRelView.addItemDecoration(divider)


    }

    override fun lazyLoad() {
    }

    override fun initEvent() {
        me_img.setOnClickListener {
            LoginActivity.actionStart(context)
        }
    }

}