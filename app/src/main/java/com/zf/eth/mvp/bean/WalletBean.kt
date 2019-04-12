package com.zf.eth.mvp.bean

data class WalletBean(
    val member: WalletMember
)

data class WalletMember(
    val walletaddress: String,
    val credit4: String, //复投账户金额
    val credit2: String //自由钱包金额
)