package com.mecm.initandroid.utils

import java.util.regex.Pattern

/**
 * 正则
 */

object SwmRegUtils {
    /**
     * 检查是否为数字
     * @param mobiles
     * @return
     */
    fun isMobileNO(mobiles: String): Boolean {
        val p = Pattern
                .compile("^1(3[0-9]|4[57]|5[0-35-9]|7[0135678]|8[0-9])\\d{8}$")
        val m = p.matcher(mobiles)
        return m.matches()
    }
}
