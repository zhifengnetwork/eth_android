package com.zf.eth.mvp.bean

data class C2cBean(
    val list:List<C2cList>,
    val total:String,
    val pagesize:String
)
data class C2cList(
    val id:String,
    val openid:String,
    val openid2:String,
    val money:String,
    val createtime:String,
    val type:String,
    val price:String,
    val trx:String,
    val status:String,
    val nickname:String,
    val zfbfile:String,
    val wxfile:String,
    val bankid:String,
    val bankname:String,
    val bank:String,
    val nickname2:String,
    val self:String,
    val self3:String

)