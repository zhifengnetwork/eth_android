package com.zf.eth.mvp.bean

data class WinBean(
    val list: List<WinList>,
    val pagesize: Int,
    val total: Int
)

data class WinList(
    val id:String,
    val money: String,
    val createtime: String,
    val ranking: String,
    val number: String,
    val numberid: String,
    val type:String
)