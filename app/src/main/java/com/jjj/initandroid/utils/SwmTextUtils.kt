package com.jjj.initandroid.utils

import android.widget.TextView

/**
 * Created by IsSwm on 2017/4/24.
 */

object SwmTextUtils {

    //    判断文本控件 是否为空
    fun isNull(textView: TextView): Boolean {
        val trim = textView.text.toString().trim { it <= ' ' }
        return SwmStringUtils.isEmpty(getText(textView))
    }

    //    获取文本控件 的内容
    fun getText(textView: TextView): String {
        return textView.text.toString().trim { it <= ' ' }
    }


}
