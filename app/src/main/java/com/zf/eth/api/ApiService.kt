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
     * Banner 首页轮播图
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun getBanner(@Field("r") r: String): Observable<BaseBean<List<BannerBean>>>

    /**
     * 首页公告
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun getNotice(@Field("r") r: String): Observable<NoticeBean>

    /**
     * 登录
     * app/index.php?i=12&c=entry&m=ewei_shopv2&do=mobile&r=account.login
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun login(
        @Field("r") r: String,
        @Field("mobile") mobile: String,
        @Field("pwd") pwd: String
    ): Observable<LoginBean>

    /**
     * 用户信息
     * app/index.php?i=12&c=entry&m=ewei_shopv2&do=mobile&r=index.trx
     * 上面的api不对
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun getUserInfo(@Field("r") r: String): Observable<UserInfoBean>

    /**
     * 首页接口
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun getHome(
        @Field("r") r: String,
        @Field("openid") openid: String,
        @Field("id") id: String
    ): Observable<BaseBean<HomeBean>>

    /**
     * 投资记录
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun getInvest(
        @Field("r") r: String,
        @Field("openid") openid: String,
        @Field("id") id: String,
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
        @Field("id") id: String
    ): Observable<BaseBean<List<TeamBean>>>

}