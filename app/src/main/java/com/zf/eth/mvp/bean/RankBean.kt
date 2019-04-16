package com.zf.eth.mvp.bean

data class RankBean(
        val total: String,
        val winning: List<RankWinBean>,
        val today: List<RankTodayBean>,
        val yestoday: List<RankYesBean>
)

data class RankWinBean(
        val id: String,
        val uniacid: String,
        val openid: String,
        val numberid: String,
        val stakesum: String,
        val money: String,
        val createtime: String,
        val type: String,
        val ranking: String,
        val number: String
)

data class RankTodayBean(
        val id: String,
        val openid: String,
        val avatar: String,
        val nickname: String,
        val mobile: String,
        val moneys: String,
        val yuji: String,
        val type: String,
        val yujis: String,
        val bfb: String
)

data class RankYesBean(
        val id: String,
        val openid: String,
        val avatar: String,
        val nickname: String,
        val mobile: String,
        val moneys: String,
        val type: String,
        val yujis: String,
        val bfb: String
)