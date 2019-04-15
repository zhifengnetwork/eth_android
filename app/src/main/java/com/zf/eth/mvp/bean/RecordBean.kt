package com.zf.eth.mvp.bean

data class RecordBean(
    val list: List<RecordList>,
    val pagesize: Int,
    val total: Int
)

data class RecordList(
    val id: String,
    val money: String,
    val createtime: String,
    val number: String,
    val multiple: String,
    val thigh: String
)