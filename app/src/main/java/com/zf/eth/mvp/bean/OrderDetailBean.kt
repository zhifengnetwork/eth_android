package com.zf.eth.mvp.bean


data class OrderDetailBean(
    val list: OrderDetailList,
    val type_own: String
)

data class OrderDetailList(
    val uniacid: String,
    val openid: String,
    val openid2: String,
    val price: String,
    val trx: String,
    val trx2: String,
    val money: String,
    val file: String,
    val type: String,
    val status: String,
    val createtime: String,
    val endtime: String,
    val id: String,
    val apple_time: String,
    val sxf0: String,
    val nickname: String,
    val mobile: String,
    val zfbfile: String,
    val wxfile: String,
    val bankid: String,
    val bankname: String,
    val bank: String,
    val nickname2: String,
    val mobile2: String,
    val zfbfile2: String,
    val wxfile2: String,
    val bankid2: String,
    val bankname2: String,
    val bank2: String

)