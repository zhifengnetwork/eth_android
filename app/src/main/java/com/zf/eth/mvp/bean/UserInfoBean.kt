package com.zf.eth.mvp.bean

data class UserInfoBean(
    val status: Int,
    val result: InfoResult
)

data class InfoResult(
    val message: String
)