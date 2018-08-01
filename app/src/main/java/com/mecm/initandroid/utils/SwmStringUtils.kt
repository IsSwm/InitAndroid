package com.mecm.initandroid.utils

object SwmStringUtils {
    /**
     * 判断字符串是否有值，如果为null或者是空字符串或者只有空格或者为"null"字符串，则返回true，否则则返回false
     */
    fun isEmpty(value: String?): Boolean {
        return !(value != null && !"".equals(value.trim { it <= ' ' }, ignoreCase = true)
                && !"null".equals(value.trim { it <= ' ' }, ignoreCase = true))
    }
}
