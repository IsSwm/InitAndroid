package com.mecm.initandroid.utils

import android.support.annotation.NonNull
import android.widget.TextView
import java.text.DecimalFormat

/**
 * Created by IsSwm on 2017/4/24.
 */

object SwmTextUtils {

    //    判断文本控件 是否为空  EditText调用也管用
    fun isNull(textView: TextView): Boolean {
        val trim = textView.text.toString().trim { it <= ' ' }
        return SwmStringUtils.isEmpty(getText(textView))
    }

    //    判断文本控件 是否为空  EditText调用也管用
    fun isNullToast(textView: TextView, tip: String): Boolean {
        if(isNull(textView)){
            SwmToastUtils.showSingletonToast(tip+"不能为空!")
        }
        return isNull(textView)
    }

    //    获取文本控件 的内容
    fun getText(textView: TextView): String {
        return textView.text.toString().trim { it <= ' ' }
    }

    // 替换字符串指定内容
    fun replaceText(str: String, fromIndex: Int, toIndex: Int, newStr: String): String {
        //字符串截取
        val bb = str.substring(fromIndex, toIndex)
        //字符串替换
        return str.replace(bb, newStr)
    }

    // 截取 字符串指定内容
    fun subString(@NonNull str: String, fromIndex: Int, toIndex: Int): String {
        val toindex: Int = if (str.length <= toIndex) {
            str.length
        } else {
            toIndex
        }
        //字符串截取
        return str.substring(fromIndex, toindex)
    }


    // 消除科学技术法
    fun doubleToString(d: Double): String? {
        // 对Double类型的数字进行 格式化输出
        val df = DecimalFormat("###############0.########")// 最多保留几位小数，就用几个#，最少位就用0来确定
        return df.format(d)
    }
}
