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

    /**
     * c2c挂卖中心  获取福彩3D下注记录
     * app/index.php?i=12&c=entry&m=ewei_shopv2&do=mobile&r=member.androidapi.guamairecordjilu
     */
   @POST("app/index.php")
   @FormUrlEncoded
   fun getC2c(
        @Field("r") r: String,
        @Field("userid") userid: String,
        @Field("page") page: String,
        @Field("status") status: String,
        @Field("type") type: String
    ):Observable<BaseBean<C2cBean>>

    /**
     * 我的-支付管理信息
     * app/index.php?i=12&c=entry&m=ewei_shopv2&do=mobile&r=member.androidapi.pay_management
     */
    @POST("app/index.php")
    @FormUrlEncoded
    fun getPayManage(
        @Field("r") r: String,
        @Field("userid") userid: String
    ):Observable<BaseBean<PayManageBean>>

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
    ):Observable<BaseBean<Unit>>

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
    ):Observable<BaseBean<BulletinBean>>

    /**
     * 我的-app下载
     */
    @POST("app/index.php")
    @FormUrlEncoded
    fun getDownload(@Field("r") r: String):Observable<BaseBean<DownloadBean>>

    /**
     * 我的-邀请
     */
    @POST("app/index.php")
    @FormUrlEncoded
    fun getInvite(@Field("r") r: String,@Field("userid") userid: String):Observable<BaseBean<InviteBean>>
}
