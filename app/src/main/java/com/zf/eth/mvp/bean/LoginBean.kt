package com.zf.eth.mvp.bean

data class LoginBean(
        val status: Int,
        val result: LoginResult
)

data class LoginResult(
        val message: String,
        val url: String,
        val user_id: String,
        val openid: String
)