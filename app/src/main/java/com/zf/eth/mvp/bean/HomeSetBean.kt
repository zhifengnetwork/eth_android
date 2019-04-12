package com.zf.eth.mvp.bean

data class HomeSetBean(
    val slide: List<HomeSlide>,
    val data: HomeData,
    val notice: List<HomeNotice>
)

//幻灯片
data class HomeSlide(
    val advname: String,
    val thumb: String
)

data class HomeData(
    val touzimoney: String,
    val shouyimoneysum: String,
    val shouyimoney: String,
    val money: String,
    val xiaji: String
)

data class HomeNotice(
    val detail: String
)