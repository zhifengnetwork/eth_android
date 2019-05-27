package com.zf.eth.ui.fragment.wallet

import android.view.View
import com.zf.eth.R
import com.zf.eth.base.NotLazyBaseFragment
import com.zf.eth.livedata.UserInfoLiveData
import com.zf.eth.mvp.bean.ChargeBean
import com.zf.eth.mvp.bean.WalletBean
import com.zf.eth.mvp.contract.WalletContract
import com.zf.eth.mvp.presenter.WalletPresenter
import com.zf.eth.showToast
import com.zf.eth.ui.activity.MainActivity
import com.zf.eth.ui.activity.TransferActivity
import com.zf.eth.ui.activity.VotingActivity
import com.zf.eth.ui.activity.WithdrawActivity
import com.zf.eth.utils.LogUtils
import kotlinx.android.synthetic.main.fragment_wallet.*

class WalletFragment : NotLazyBaseFragment(), WalletContract.View {

    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)

    }

    override fun setWallet(bean: WalletBean) {
        reAccount.text = bean.member.credit4
        freeAccount.text = bean.member.credit2
    }

    override fun setChart(bean: ChargeBean) {
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    companion object {
        fun newInstance(): WalletFragment {
            return WalletFragment()
        }
    }

    private val walletPresenter by lazy { WalletPresenter() }

    override fun getLayoutId(): Int = R.layout.fragment_wallet


    override fun onDestroy() {
        super.onDestroy()
        walletPresenter.detachView()
    }


    override fun initView() {
        walletPresenter.attachView(this)

        dashLine1.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        dashLine2.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        //如果账户因退出机制锁定 隐藏复投 显示自由钱包(且自由钱包下只有提币)
        if (UserInfoLiveData.value?.member?.type == "2") {
            //复投布局
            reAccountLayout.visibility = View.GONE
            //自由钱包 -> 一件复投
            freeVoting.visibility = View.GONE
            freeVoting_sp.visibility = View.GONE
            //C2C按钮
            c2c.visibility = View.GONE
            //棋牌娱乐
            freeGame.visibility = View.GONE
            //互转
            transfer.visibility = View.GONE
        }
        //用户赚取收益达到3倍而锁定 复投只显示 一键复投 自由钱包显示 一键投币 提币
        if (UserInfoLiveData.value?.member?.suoding == "1") {
            //复投账户 -> 棋牌娱乐
            reGame.visibility = View.GONE
            //自由钱包 -> C2C按钮
            c2c.visibility = View.GONE
            //棋牌娱乐
            freeGame.visibility = View.GONE
            //互转
            transfer.visibility = View.GONE
        }
    }

    override fun onResume() {
        lazyLoad()
        super.onResume()
    }

    override fun lazyLoad() {
        walletPresenter.requestWallet()
    }

    override fun initEvent() {

        //复投账户->棋牌娱乐
        reGame.setOnClickListener {
            MainActivity.actionStart(context, 1)
            activity?.finish()
        }

        //自由钱包->棋牌娱乐
        freeGame.setOnClickListener {
            MainActivity.actionStart(context, 1)
            activity?.finish()
        }


        //首页C2C
        c2c.setOnClickListener {
            MainActivity.actionStart(context, 2)
            activity?.finish()
        }

        //互转
        transfer.setOnClickListener {
            TransferActivity.actionStart(context)
        }

        //提币
        withdraw.setOnClickListener {
            WithdrawActivity.actionStart(context)
        }

        //复投账户一键复投
        reVoting.setOnClickListener {
            VotingActivity.actionStart(context, VotingActivity.RE_DELIVER)
        }

        //自由钱包一键复投
        freeVoting.setOnClickListener {
            VotingActivity.actionStart(context, VotingActivity.FREE_WALLET)
        }


        /**复投账户点击展开 后面如果需要就解除注释*/
        reAccountLayout.setOnClickListener {
            reAccountLayout.isSelected = !reAccountLayout.isSelected
            if (reAccountLayout.isSelected) {
                reDetailLayout.visibility = View.VISIBLE
            } else {
                reDetailLayout.visibility = View.GONE
            }
        }

        /**自由钱包点击展开 后面如果需要就解除注释 */
        freeAccountLayout.setOnClickListener {
            freeAccountLayout.isSelected = !freeAccountLayout.isSelected
            if (freeAccountLayout.isSelected) {
                freeDetailLayout.visibility = View.VISIBLE
            } else {
                freeDetailLayout.visibility = View.GONE
            }
        }
    }
}