package com.jjj.jiatingfuwuqkl.utils

import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

/**
 * @author MaTianyu
 * *
 * @date 2014-07-31
 */
object SwmToastUtils {

    private var mToast: Toast? = null
    private val context = SwmUIUtils.context


    //  获取 一个单独的toast
    fun getSingletonToast(resId: Int): Toast {
        if (mToast == null) {
            mToast = Toast.makeText(context, resId, Toast.LENGTH_SHORT)
        } else {
            mToast!!.setText(resId)
        }
        return mToast!!
    }

    fun getSingletonToast(text: String): Toast {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
        } else {
            mToast!!.setText(text)
        }
        return mToast!!
    }

    fun getSingleLongToast(resId: Int): Toast {
        if (mToast == null) {
            mToast = Toast.makeText(context, resId, Toast.LENGTH_LONG)
        } else {
            mToast!!.setText(resId)
        }
        return mToast!!
    }

    fun getSingleLongToast(text: String): Toast {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, Toast.LENGTH_LONG)
        } else {
            mToast!!.setText(text)
        }
        return mToast!!
    }

    fun getToast(resId: Int): Toast {
        return Toast.makeText(context, resId, Toast.LENGTH_SHORT)
    }

    fun getToast(text: String): Toast {
        return Toast.makeText(context, text, Toast.LENGTH_SHORT)
    }

    fun getLongToast(resId: Int): Toast {
        return Toast.makeText(context, resId, Toast.LENGTH_LONG)
    }

    fun getLongToast(text: String): Toast {
        return Toast.makeText(context, text, Toast.LENGTH_LONG)
    }

    fun showSingletonToast(resId: Int) {
        getSingletonToast(resId).show()
    }


    fun showSingletonToast(text: String) {
        getSingletonToast(text).show()
    }

    fun showSingleLongToast(resId: Int) {
        getSingleLongToast(resId).show()
    }


    fun showSingleLongToast(text: String) {
        getSingleLongToast(text).show()
    }

    fun showToast(resId: Int) {
        getToast(resId).show()
    }

    fun showToast(text: String) {
        getToast(text).show()
    }

    fun showLongToast(resId: Int) {
        getLongToast(resId).show()
    }

    fun showLongToast(text: String) {
        getLongToast(text).show()
    }

    /**
     * 显示Toast 并且 设置指定的字体大小
     * @param text  显示文本
     * *
     * @param size  文本 大小
     */
    fun showAndSetSizeToast(text: String, size: Int) {
        val toast = getSingletonToast(text)
        val layout = toast.view as LinearLayout
        val tv = layout.getChildAt(0) as TextView
        tv.textSize = size.toFloat()

        toast.setText(text)
        toast.show()
    }

}
