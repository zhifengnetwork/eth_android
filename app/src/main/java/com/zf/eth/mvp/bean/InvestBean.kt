package com.zf.eth.mvp.bean

data class InvestBean(

//    val result: InvestResult
    val total: Int,
    val pagesize: Int,
    val list: List<InvestList>
)

//data class InvestResult(
//
//)

data class InvestList(
    val openid: String,
    val title: String,
    val createtime: String,
    val money: String,
    val RMB:String,
    val status:String,
    val nickname:String,
    val money2:String
)