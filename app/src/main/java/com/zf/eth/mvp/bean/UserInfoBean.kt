package com.zf.eth.mvp.bean

data class UserInfoBean(
        val member: Member,
        val huiyuanlevel: Level
)

data class Member(
        val walletaddress: String,
        val avatar: String,
        val nickname: String,
        val id: String
)

data class Level(
        val levelname1: String?, //会员等级
        val levelname3: String?//市场等级
)