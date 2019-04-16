package com.zf.eth.api

import com.zf.eth.base.BaseBean
import com.zf.eth.mvp.bean.*
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


/**
 * Api 接口
 */

interface ApiService {

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun login(
            @Field("r") r: String,
            @Field("mobile") mobile: String,
            @Field("pwd") pwd: String
    ): Observable<BaseBean<LoginBean>>

    /**
     * 我的
     * 用户信息
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun getUserInfo(
            @Field("r") r: String,
            @Field("userid") userid: String
    ): Observable<BaseBean<UserInfoBean>>

    /**
     * 首页接口
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun getHome(
            @Field("userid") userid: String
    ): Observable<BaseBean<HomeSetBean>>

    /**
     * 投资记录
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun getInvest(
            @Field("r") r: String,
            @Field("userid") id: String,
            @Field("type") type: String,
            @Field("page") page: Int
    ): Observable<BaseBean<InvestBean>>

    /**
     * 团队列表
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun getTeam(
            @Field("r") r: String,
            @Field("userid") id: String,
            @Field("page") page: Int
    ): Observable<BaseBean<TeamBean>>

    /**
     * 游戏规则
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun getGameRules(
            @Field("r") r: String,
            @Field("userid") userid: String
    ): Observable<BaseBean<String>>

    /**
     * 投资收益
     * 分今日收益和总收益
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun getEarn(
            @Field("r") r: String,
            @Field("userid") id: String,
            @Field("type") type: String
    ): Observable<BaseBean<EarnBean>>

    /**
     * 我的钱包
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun getWallet(
            @Field("r") r: String,
            @Field("userid") userid: String
    ): Observable<BaseBean<WalletBean>>

    /**
     * 下注接口
     * 1.确认信息
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun requestBet(
            @Field("r") r: String,
            @Field("userid") userid: String,
            @Field("type") type: Int,
            @Field("payment") payment: Int?,
            @Field("money") money: String?,
            @Field("list") list: Array<Array<String>>?
    ): Observable<BaseBean<BetBean>>

    /**
     * 下注接口
     * 2.确认下注
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun requestConfirmBet(
            @Field("r") r: String,
            @Field("userid") userid: String,
            @Field("type") type: Int,
            @Field("payment") payment: Int?,
            @Field("money") money: String?,
            @Field("list") list: Array<Array<String>>?
    ): Observable<BaseBean<List<Unit>>>

    /**
     * 钱包提现
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun requestWithDraw(
            @Field("r") r: String,
            @Field("userid") userid: String,
            @Field("money") money: String?
    ): Observable<BaseBean<String>>

    /**
     * 转账
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun requestTransfer(
            @Field("r") r: String,
            @Field("userid") userid: String,
            @Field("money") money: String,
            @Field("id") id: String
    ): Observable<BaseBean<List<Unit>>>

    /**
     * 手续费
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun requestCharge(
            @Field("r") r: String,
            @Field("userid") userid: String
    ): Observable<BaseBean<ChargeBean>>

    /**
     *c2c挂卖中心  获取福彩3D下注记录
     */
    @POST("app/index.php")
    @FormUrlEncoded
    fun getC2c(
            @Field("r") r: String,
            @Field("userid") userid: String,
            @Field("page") page: String,
            @Field("status") status: String,
            @Field("type") type: String
    ): Observable<BaseBean<C2cBean>>

    /**
     * 我的-支付管理信息
     * app/index.php?i=12&c=entry&m=ewei_shopv2&do=mobile&r=member.androidapi.pay_management
     */
    @POST("app/index.php")
    @FormUrlEncoded
    fun getPayManage(
            @Field("r") r: String,
            @Field("userid") userid: String
    ): Observable<BaseBean<PayManageBean>>

    /**
     * 我的-支付管理提交|钱包地址
     */
    @POST("app/index.php")
    @FormUrlEncoded
    fun setPayMange(
            @Field("r") r: String,
            @Field("userid") userid: String,
            @Field("adress") adress: String,
            @Field("url") url: String,
            @Field("zfbfile") zfbfile: String,
            @Field("wxfile") wxfile: String,
            @Field("bankid") bankid: String,
            @Field("bankname") bankname: String,
            @Field("bank") bank: String
    ): Observable<BaseBean<Unit>>

    /**
     * 我的-平台公告
     */
    @POST("app/index.php")
    @FormUrlEncoded
    fun getBulletin(
            @Field("r") r: String,
            @Field("userid") userid: String,
            @Field("page") page: String,
            @Field("cateid") cateid: String
    ): Observable<BaseBean<BulletinBean>>

    /**
     * 我的-app下载
     */
    @POST("app/index.php")
    @FormUrlEncoded
    fun getDownload(@Field("r") r: String): Observable<BaseBean<DownloadBean>>

    /**
     * 我的-邀请
     */
    @POST("app/index.php")
    @FormUrlEncoded
    fun getInvite(
            @Field("r") r: String,
            @Field("userid") userid: String
    ): Observable<BaseBean<InviteBean>>

    /**
     * 3D下注首页
     *  开奖号码 每注多少钱
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun requestGameHome(
            @Field("r") r: String,
            @Field("userid") userid: String
    ): Observable<BaseBean<GameHomeBean>>

    /**
     * 3D下注记录
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun requestRecord(
            @Field("r") r: String,
            @Field("userid") userid: String,
            @Field("pagesize") pagesize: Int,
            @Field("page") page: Int?
    ): Observable<BaseBean<RecordBean>>

    /**
     * 3D中奖记录
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun requestWin(
            @Field("r") r: String,
            @Field("userid") userid: String,
            @Field("page") page: Int?
    ): Observable<BaseBean<WinBean>>

    /**
     * 发送短信
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun requestVerifyCode(
            @Field("r") r: String,
            @Field("imgcode") imgcode: String,
            @Field("mobile") mobile: String,
            @Field("temp") temp: String
    ): Observable<BaseBean<Unit>>

    /**
     * 修改密码
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun requestChangePwd(
            @Field("r") r: String,
            @Field("userid") userid: String,
            @Field("mobile") mobile: String,
            @Field("code") code: String,
            @Field("pwd") pwd: String
    ): Observable<BaseBean<Unit>>

    /**
     * 注册账号或者找回密码
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun requestRegister(
            @Field("r") r: String,
            @Field("type") type: String,
            @Field("mobile") mobile: String,
            @Field("code") code: String,
            @Field("pwd") pwd: String
    ): Observable<BaseBean<RegisterBean>>

    /**
     * 激活账户-投资购买
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun requestBuyInfo(
            @Field("r") r: String,
            @Field("userid") userid: String
    ): Observable<BaseBean<BuyBean>>

    /**
     * 激活账户-上传支付凭证
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun requestPayImg(
            @Field("r") r: String,
            @Field("file") file: String,
            @Field("userid") userid: String
    ): Observable<BaseBean<Unit>>

    /**
     * 激活账户-投资购买
     * 确定付款
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun requestConfirmPay(
            @Field("r") r: String,
            @Field("userid") userid: String,
            @Field("money") money: String,
            @Field("url") url: String
    ): Observable<BaseBean<Unit>>

    /**
     * 一键复投
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun requestRePay(
            @Field("r") r: String,
            @Field("userid") userid: String,
            @Field("money") money: String,
            @Field("type") type: String
    ): Observable<BaseBean<List<Unit>>>

    /**
     * 投资排行
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun requestRank(
            @Field("r") r: String,
            @Field("userid") userid: String
    ): Observable<BaseBean<RankBean>>

}

