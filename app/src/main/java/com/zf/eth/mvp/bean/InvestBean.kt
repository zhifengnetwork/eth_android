package com.zf.eth.mvp.bean

data class InvestBean(

    val result: InvestResult

)

data class InvestResult(
    val total: Int,
    val pagesize: Int,
    val list: List<InvestList>
)

data class InvestList(
    val openid: String,
    val title: String,
    val createtime: String,
    val money: String,
    val RMB:String,
    val status:String
)