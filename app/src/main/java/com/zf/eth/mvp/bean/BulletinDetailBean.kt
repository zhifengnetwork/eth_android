package com.zf.eth.mvp.bean

data class BulletinDetailBean(
    val article: BulletinDetail
)

data class BulletinDetail(
    val product_advs_link: String,
    val article_linkurl: String,
    val product_advs_more: String,
    val product_advs_title: String,
    val product_advs_type: String,
    val article_report: String,
    val article_content: String,
    val article_areas: String,
    val article_mp: String,
    val article_date_v: String,
    val article_author: String,
    val article_title: String
)