package com.zf.eth.mvp.bean

data class PayManageBean(
    val walletaddress: String,
    val walletcode: String?,
    val bankid: String,
    val bankname: String,
    val bank: String,
    val zfbfile: String,
    val wxfile: String
)