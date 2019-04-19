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
            @Field("list") list: String?
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
            @Field("list") list: String?
    ): Observable<BaseBean<Unit>>

    /**
     * 钱包提现
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun requestWithDraw(
            @Field("r") r: String,
            @Field("userid") userid: String,
            @Field("money") money: String?
    ): Observable<BaseBean<Unit>>

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
    ): Observable<BaseBean<Unit>>

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
            @Field("page") page: Int,
            @Field("type") type: String
    ): Observable<BaseBean<C2cBean>>

    /**
     *c2c点击卖出或者买入按钮
     */
    @POST("app/index.php")
    @FormUrlEncoded
    fun setSellout(
            @Field("r") r: String,
            @Field("userid") userid: String,
            @Field("id") id: String,
            @Field("type") type: String

    ): Observable<BaseBean<Unit>>


    /**
     *c2c中心-全部订单
     */
    @POST("app/index.php")
    @FormUrlEncoded
    fun getMyOrder(
            @Field("r") r: String,
            @Field("userid") userid: String,
            @Field("status") status: String

    ): Observable<BaseBean<MyOrderBean>>

    /**
     *c2c中心-全部订单
     */
    @POST("app/index.php")
    @FormUrlEncoded
    fun getOrderDetail(@Field("r") r: String,
                       @Field("userid") userid: String,
                       @Field("id")id:String
    ):Observable<BaseBean<OrderDetailBean>>

    /**
     *c2c中心点击卖出或者买入按钮
     */
    @POST("app/index.php")
    @FormUrlEncoded
    fun setHangonsale(
            @Field("r") r: String,
            @Field("userid") userid: String,
            @Field("type") type: String,
            @Field("price") price: String,
            @Field("money") money: String,
            @Field("sxf0") sxf0: String,
            @Field("trx") trx: String,
            @Field("trx2") trx2: String
    ): Observable<BaseBean<Unit>>


    /**
     *c2c中心--撤销订单
     */
    @POST("app/index.php")
    @FormUrlEncoded
    fun requestCancelOrder(
            @Field("r") r: String,
            @Field("userid") userid: String,
            @Field("id") id: String

    ): Observable<BaseBean<Unit>>

    /**
     *c2c中心--获取申诉
     */
    @POST("app/index.php")
    @FormUrlEncoded
    fun getAppeal(
            @Field("r") r: String,
            @Field("userid") userid: String,
            @Field("page") page: String

    ): Observable<BaseBean<AppealBean>>

    /**
     *c2c中心--获取申诉详情
     */
    @POST("app/index.php")
    @FormUrlEncoded
    fun getAppealList(
            @Field("r") r: String,
            @Field("userid") userid: String,
            @Field("id") id: String
    ): Observable<BaseBean<AppealListBean>>

    /**
     *c2c中心--确认申诉2c中心--确认申诉
     */
    @POST("app/index.php")
    @FormUrlEncoded
    fun setComplain(
            @Field("r") r: String,
            @Field("userid") userid: String,
            @Field("id") id: String,
            @Field("files") files: String,
            @Field("text") text: String,
            @Field("textarea") textarea: String
    ): Observable<BaseBean<Unit>>

    /**
     *c2c中心--确认收款/打款
     */
    @POST("app/index.php")
    @FormUrlEncoded
    fun setConfirmOrder(
            @Field("r") r: String,
            @Field("userid") userid: String,
            @Field("id") id: String,
            @Field("file") file: String
    ): Observable<BaseBean<Unit>>

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
    ): Observable<BaseBean<ImageViewBean>>

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
    ): Observable<BaseBean<Unit>>

    /**
     * 一键复投 获取信息
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun requestRePayInfo(
            @Field("r") r: String,
            @Field("userid") userid: String
    ): Observable<BaseBean<RePayInfoBean>>

    /**
     * 投资排行
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun requestRank(
            @Field("r") r: String,
            @Field("userid") userid: String
    ): Observable<BaseBean<RankBean>>

    /**
     * 上传头像
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun requestChangeHead(
            @Field("r") r: String,
            @Field("userid") userid: String,
            @Field("file") file: String
    ): Observable<BaseBean<ImageViewBean>>

    /**
     * 修改用户信息
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun requestChangeInfo(
            @Field("r") r: String,
            @Field("userid") userid: String,
            @Field("nickname") nickname: String,
            @Field("avatar") avatar: String
    ): Observable<BaseBean<Unit>>

    /**
     * 退出机制
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun requestLogOut(
            @Field("r") r: String,
            @Field("userid") userid: String,
            @Field("money") money: String
    ): Observable<BaseBean<Unit>>

}

