package com.zf.eth.mvp.bean

data class TeamBean(
    val list: List<TeamList>,
    val pagesize: Int,
    val total: Int
)

data class TeamList(
    val nickname: String,
    val mobile: String,
    val createtime: String,
    val agenttime: String,
    val type:Int
)