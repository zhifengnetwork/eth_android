package com.zf.eth.mvp.bean

data class InvestBean(

//    val result: InvestResult
    val total: Int,
    val pagesize: Int,
    val list: List<InvestList>?
)

//data class InvestResult(
//
//)

data class InvestList(
    val openid: String,
    val title: String,
    val createtime: String,
    val money: String,
    val realmoney: String,
    val openid2: String? = "",
    val type: String,
    val RMB: String,
    val charge: String,
    val status: String,
    val payment: String, //1自由账户 2复投账户
    val after_money: String,
    val nickname: String,
    val money2: String
)