package com.zf.eth.mvp.bean

data class AppealBean(
    val list:List<AppealList>
)
data class AppealList(
    val id :String,
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
    val files:String
)