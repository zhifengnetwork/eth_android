package com.zf.eth.api

import com.zf.eth.base.BaseBean
import com.zf.eth.mvp.bean.BannerBean
import com.zf.eth.mvp.bean.NoticeBean
import io.reactivex.Observable
import retrofit2.http.*


/**
 * Api 接口
 */

interface ApiService {

    /**
     * Banner 首页轮播图
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun getBanner(
        @Field("i") i: String,
        @Field("c") c: String,
        @Field("m") m: String,
        @Field("do") dso: String,
        @Field("r") r: String
    ): Observable<BaseBean<List<BannerBean>>>

    /**
     * 首页公告
     */
    @FormUrlEncoded
    @POST("app/index.php")
    fun getNotice(
        @Field("i") i: String,
        @Field("c") c: String,
        @Field("m") m: String,
        @Field("do") dso: String,
        @Field("r") r: String
    ): Observable<BaseBean<List<NoticeBean>>>

}