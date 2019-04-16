package com.zf.eth.mvp.bean

data class BuyBean(
    val list: BuyInfo
)

data class BuyInfo(
    val zfb: String,
    val wx: String,
    val credit1: String,
    val type: String,
    val bibi: String,
    val weixinfile: String,
    val add: String
)