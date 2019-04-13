package com.zf.eth.mvp.bean

data class GameHomeBean(
    val price: String,
    val sale1: List<LotteryNumber>?
)

data class LotteryNumber(
    val number: String,
    val datetime: String,
    val time: String
)