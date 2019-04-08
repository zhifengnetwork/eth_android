package com.zf.eth.base

import java.io.Serializable

class BaseBean<T>(
    val status: Int,
    val list: T
) : Serializable