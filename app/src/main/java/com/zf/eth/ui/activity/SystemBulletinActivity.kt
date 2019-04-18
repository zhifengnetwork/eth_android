package com.zf.eth.ui.activity

import android.content.Context
import android.content.Intent
import com.zf.eth.R
import com.zf.eth.base.BaseActivity
import com.zf.eth.base.BaseFragmentAdapter
import com.zf.eth.mvp.bean.BulletinBean
import com.zf.eth.mvp.contract.BulletinContract
import com.zf.eth.mvp.presenter.BulletinPresenter
import com.zf.eth.ui.fragment.bulletin.ClassifyFragment
import kotlinx.android.synthetic.main.activity_system_bulletin.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class SystemBulletinActivity : BaseActivity(), BulletinContract.View {

    override fun showError(msg: String, errorCode: Int) {

    }

    override fun getBulletin(bean: BulletinBean) {
        titleName.text = bean.article_sys.article_title

        val mFragment = ArrayList<ClassifyFragment>()
        val titles = ArrayList<String>()
        repeat(bean.categorys.size) { i ->
            mFragment.add(ClassifyFragment.buildFragment(bean.categorys[i].id))
            titles.add(bean.categorys[i].category_name)
        }

        val mAdapter = BaseFragmentAdapter(supportFragmentManager, mFragment, titles)
        bulletin_vp.adapter = mAdapter
        bulletin_tab.setupWithViewPager(bulletin_vp)
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

    companion object {
        fun actionStart(context: Context?) {
            context?.startActivity(Intent(context, SystemBulletinActivity::class.java))
        }
    }

    override fun initToolBar() {
        titleName.text = "平台公告"
        backLayout.setOnClickListener { finish() }
    }

    override fun layoutId(): Int = R.layout.activity_system_bulletin

    private val presenter by lazy { BulletinPresenter() }

    override fun initData() {

    }

    override fun initView() {
        presenter.attachView(this)

    }

    override fun initEvent() {

    }

    override fun start() {
        presenter.requseBulletin("1", "")
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

}