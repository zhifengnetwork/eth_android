package com.zf.eth.mvp.bean

data class UserInfoBean(
        val member: Member,
        val huiyuanlevel: Level,
        val money4: String, //可退金额
        val arr2: AllInvest
)

data class AllInvest(
        val money: String
)

data class Member(
        val walletaddress: String,
        val avatar: String,
        val nickname: String,
        val id: String,
        val type: String
)

data class Level(
        val levelname1: String?, //会员等级
        val levelname3: String?//市场等级
)