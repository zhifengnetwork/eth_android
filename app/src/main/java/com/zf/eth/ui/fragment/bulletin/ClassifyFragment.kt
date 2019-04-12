package com.zf.eth.ui.fragment.bulletin

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.zf.eth.R
import com.zf.eth.base.BaseFragment
import com.zf.eth.mvp.bean.Articles
import com.zf.eth.mvp.bean.BulletinBean
import com.zf.eth.mvp.contract.BulletinContract
import com.zf.eth.mvp.presenter.BulletinPresenter
import com.zf.eth.ui.adapter.SystemBulletinIfyAdapter
import kotlinx.android.synthetic.main.layout_bullentin_content.*

class ClassifyFragment:BaseFragment(),BulletinContract.View{
    override fun showError(msg: String, errorCode: Int) {

    }

    override fun getBulletin(bean: BulletinBean) {
          articles.addAll(bean.articles)
          adapter.notifyDataSetChanged()
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

    private val presenter by lazy { BulletinPresenter() }

    private var mType =""
    //在真正的开发中，每个界面的ID可能是不同的，所以这里会接收一个ID
    companion object {
        fun buildFragment(type: String): ClassifyFragment {
            val fragment = ClassifyFragment()
            fragment.mType= type
            return fragment
        }
    }
    private val articles=ArrayList<Articles>()
    private val adapter by lazy { SystemBulletinIfyAdapter(context,articles) }
    override fun getLayoutId(): Int = R.layout.layout_bullentin_content

    override fun initView() {
        presenter.attachView(this)
         bulletin_rv.layoutManager=LinearLayoutManager(context)
         bulletin_rv.adapter=adapter

    }

    override fun lazyLoad() {



    }

    override fun initEvent() {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
    override fun onStart() {
        super.onStart()
        presenter.requseBulletin("1",mType)
    }
}