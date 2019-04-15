package com.zf.eth.mvp.bean

data class EarnBean(
    val list: List<EarnList>,
    val money: String
)

data class EarnList(
    val id: String,
    val nickname: String,
    val money: String,
    val createtime: String,
    val money2: String
)