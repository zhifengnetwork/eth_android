package com.zf.eth.ui.fragment

import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.zf.eth.R
import com.zf.eth.base.BaseFragment
import com.zf.eth.ui.adapter.MeMenuAdapter
import kotlinx.android.synthetic.main.fragment_me.*

class MeFragment : BaseFragment() {

    companion object {
        fun getInstance(): MeFragment {
            return MeFragment()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_me


    private val title = arrayListOf("支付管理", "钱包地址","C2C订单","修改密码","我的邀请","平台公告","联系客服","退出机制","下载APP")
    private val img=arrayListOf(R.drawable.zhifu,R.drawable.qianbao,R.drawable.c2c_me,R.drawable.xiugaimima,R.drawable.yaoqing,R.drawable.gonggao,R.drawable.keuf,R.drawable.touzi,R.drawable.appicon3_16)

    val adapter by lazy{MeMenuAdapter(context,title,img) }

    override fun initView() {
        me_recyclerView.layoutManager=LinearLayoutManager(context)
        me_recyclerView.adapter=adapter
        me_recyclerView.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))


    }

    override fun lazyLoad() {
    }

    override fun initEvent() {
    }
}