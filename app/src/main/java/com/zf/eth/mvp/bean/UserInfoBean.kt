package com.zf.eth.mvp.bean

data class UserInfoBean(
    val member: Member
)

data class Member(
    val walletaddress: String
)