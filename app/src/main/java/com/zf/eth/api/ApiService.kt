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
     * app/index.php?i=12&c=entry&m=ewei_shopv2&do=mobile&r=account.login
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun login(
        @Field("r") r: String,
        @Field("mobile") mobile: String,
        @Field("pwd") pwd: String,
        @Field("l") l: String
    ): Observable<LoginBean>

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
        @Field("openid") openid: String,
        @Field("userid") id: String,
        @Field("type") type: String
    ): Observable<BaseBean<List<InvestBean>>>

    /**
     * 团队列表
     * app/index.php?i=12&c=entry&m=ewei_shopv2&do=mobile&r=index.xiaji&user_id=36538
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun getTeam(
        @Field("r") r: String,
        @Field("openid") openid: String,
        @Field("user_id") id: String
    ): Observable<BaseBean<List<TeamBean>>>

    /**
     * 游戏规则
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun getGameRules(
        @Field("r") r: String,
        @Field("openid") openid: String,
        @Field("id") id: String
    ): Observable<GameRulesBean>

    /**
     * 投资收益
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun getEarn(
        @Field("r") r: String,
        @Field("openid") openid: String,
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

}