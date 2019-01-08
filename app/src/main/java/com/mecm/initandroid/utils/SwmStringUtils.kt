package com.mecm.initandroid.utils

import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.text.style.ScaleXSpan

object SwmStringUtils {
    /**
     * 判断字符串是否有值，如果为null或者是空字符串或者只有空格或者为"null"字符串，则返回true，否则则返回false
     */
    fun isEmpty(value: String?): Boolean {
        return !(value != null && !"".equals(value.trim { it <= ' ' }, ignoreCase = true)
                && !"null".equals(value.trim { it <= ' ' }, ignoreCase = true))
    }
    fun stringToList(strs: String): List<String> {
        return strs.split(",")
    }
    /**
     * 将给定的字符串给定的长度两端对齐
     *
     * @param str  待对齐字符串
     * @param size 汉字个数，eg:size=5，则将str在5个汉字的长度里两端对齐
     * @Return
     */
    fun justifyString(str: String, size: Int): SpannableStringBuilder {
        val spannableStringBuilder = SpannableStringBuilder()
        if (TextUtils.isEmpty(str)) {
            return spannableStringBuilder
        }
        val chars = str.toCharArray()
        if (chars.size >= size || chars.size == 1) {
            return spannableStringBuilder.append(str)
        }
        val l = chars.size
        val scale = (size - l).toFloat() / (l - 1)
        for (i in 0 until l) {
            spannableStringBuilder.append(chars[i])
            if (i != l - 1) {
                val s = SpannableString("　")//全角空格
                s.setSpan(ScaleXSpan(scale), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                spannableStringBuilder.append(s)
            }
        }
        return spannableStringBuilder
    }
}
