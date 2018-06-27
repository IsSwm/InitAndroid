package com.jjj.jiatingfuwuqkl.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

import com.jjj.jiatingfuwuqkl.AppContext

import android.content.Context.INPUT_METHOD_SERVICE

/**
 * 软键盘操作工具
 */

object SwmSoftKeyboardUtils {

    /**
     * 弹起软键盘
     */
    fun showSoftKeyboard(activity: Activity) {
        val imm = AppContext.context!!.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(0, InputMethodManager.RESULT_SHOWN)
    }

    /**
     * 隐藏软键盘(只适用于Activity，不适用于Fragment)
     */
    fun hideSoftKeyboard(activity: Activity) {
        val view = activity.currentFocus
        if (view != null) {
            val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    /**
     * 隐藏软键盘(可用于Activity，Fragment)
     *
     * @param viewList viewList 中需要放的是当前界面所有触发软键盘弹出的控件。
     * 比如一个登陆界面， 有一个账号输入框和一个密码输入框，
     * 需要隐藏键盘的时候， 就将两个输入框对象放在 viewList 中，
     * 作为参数传到 hideSoftKeyboard 方法中即可。
     */
    fun hideSoftKeyboard(context: Context, viewList: List<View>?) {
        if (viewList == null) return
        val inputMethodManager = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

        for (v in viewList) {
            inputMethodManager.hideSoftInputFromWindow(v.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
}
