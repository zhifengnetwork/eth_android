package com.zf.eth.mvp.bean

data class NoticeBean(
    val status: Int,
    val list: List<NoticeList>
)

data class NoticeList(
    val id: String,
    val title: String,
    val detail: String
)