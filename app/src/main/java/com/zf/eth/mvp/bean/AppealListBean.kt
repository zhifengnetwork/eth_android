package com.zf.eth.mvp.bean

data class AppealListBean(
    val list:DetailList
)
data class DetailList(
    val id:String,
    val order_id:String,
    val openid:String,
    val openid2:String,
    val file:String,
    val type:String,
    val appeal_name:String,
    val text:String,
    val textarea:String,
    val stuas:String,
    val createtime:String,
    val files:String,
    val uniacid:String,
    val price:String,
    val trx:String,
    val trx2:String,
    val money:String,
    val status:String,
    val endtime:String,
    val apple_time:String,
    val sxf0:String,
    val type1:String,
    val mobile:String
)