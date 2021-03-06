package com.zf.eth.base

import java.io.Serializable

class BaseBean<T>(
    val status: Int,
    val msg: String,
    val data: T
) : Serializable