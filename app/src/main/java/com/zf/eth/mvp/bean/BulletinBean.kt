package com.zf.eth.mvp.bean

data class BulletinBean(
    val article_sys:Article,
    val categorys:List<Categorys>,
    val articles:List<Articles>
)
data class Article(
    val uniacid:String,
    val article_message:String,
    val article_title:String,
    val article_image:String,
    val article_shownum:String,
    val article_keyword:String,
    val article_temp:String,
    val article_source:String

)
data class Categorys(
    val id:String,
    val category_name:String,
    val uniacid:String,
    val displayorder:String,
    val isshow:String
)
data class Articles(
    val id:String,
    val article_title:String,
    val resp_img:String,
    val article_rule_credit:String,
    val article_rule_money:String,
    val article_author:String,
    val article_date_v:String,
    val resp_desc:String,
    val article_category:String
)