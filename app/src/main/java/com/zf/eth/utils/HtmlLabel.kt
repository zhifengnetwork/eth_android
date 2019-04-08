package com.zf.eth.utils

import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException

object HtmlLabel {
    /**
     * 过滤HTML里去除img、p、span外的所有标签
     */
    fun stringFilter(str: String): String {
        var str = str

        val regEx = "(?!<(img|p|span).*?>)<.*?>"
        val p_html = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE)
        val m_html = p_html.matcher(str)
        str = m_html.replaceAll("")

        return str.trim { it <= ' ' } // 返回文本字符串
    }

    private val REGEX_HTML = "<[^>]+>"

    fun stringHtml(strHtml: String): String {
        var strHtml = strHtml
        val p_html = Pattern.compile(REGEX_HTML, Pattern.CASE_INSENSITIVE)//下面三行是过滤html标签
        val m_html = p_html.matcher(strHtml)
        strHtml = m_html.replaceAll("")
        return strHtml
    }
}