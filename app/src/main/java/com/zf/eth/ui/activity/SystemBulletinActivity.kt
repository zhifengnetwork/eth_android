package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import android.util.Log
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.base.BaseFragmentAdapter
import com.zf.eth.mvp.bean.BulletinBean
import com.zf.eth.mvp.bean.Categorys
import com.zf.eth.mvp.contract.BulletinContract
import com.zf.eth.mvp.presenter.BulletinPresenter
import com.zf.eth.ui.adapter.SystemBulletinPagerAdapter
import com.zf.eth.ui.fragment.bulletin.ClassifyFragment
import kotlinx.android.synthetic.main.activity_system_bulletin.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class SystemBulletinActivity:BaseActivity(),BulletinContract.View{
    override fun showError(msg: String, errorCode: Int) {

    }

    override fun getBulletin(bean: BulletinBean) {
          Log.e("检测","getBulletin执行了"+bean.categorys[0].category_name)
          titleName.text=bean.article_sys.article_title

        val mFragment = ArrayList<ClassifyFragment>()
        val titles=ArrayList<String>()
        Log.e("检测","titles="+titles)


        repeat(bean.categorys.size){i->
            mFragment.add(ClassifyFragment.buildFragment(bean.categorys[i].id))
            titles.add(bean.categorys[i].category_name)
        }




        val mAdapter =BaseFragmentAdapter(supportFragmentManager,mFragment,titles)
        bulletin_vp.adapter=mAdapter
        bulletin_tab.setupWithViewPager(bulletin_vp)
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

    companion object {
        fun actionStart(context: Context?){
            context?.startActivity(Intent(context,SystemBulletinActivity::class.java))
        }
    }
    override fun initToolBar() {
        titleName.text="平台公告"
    }

    override fun layoutId(): Int = R.layout.activity_system_bulletin


    private val presenter by lazy { BulletinPresenter() }



    override fun initData() {

    }

    override fun initView() {
        presenter.attachView(this)
//        bulletin_vp.adapter=adapter
//        bulletin_tab.setupWithViewPager(bulletin_vp)

    }

    override fun initEvent() {

    }

    override fun start() {
        presenter.requseBulletin("1","")
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

}