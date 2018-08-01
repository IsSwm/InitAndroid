package com.mecm.initandroid.utils

import android.graphics.Paint
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * 一些常用的关于 view 的 设置
 * 设置高度宽度的封装
 */

object SwmViewUtils {
    //    为TextView设置下划线
    fun setUnderLine(textView: TextView) {
        textView.paint.flags = Paint.UNDERLINE_TEXT_FLAG //下划线
        textView.paint.isAntiAlias = true//抗锯齿
    }

    //    设置 view 是否显示或隐藏
    fun isShowOrGone(view: View, isShow: Boolean) {
        if (isShow) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }

    /**
     * 注意：状态栏的高度没有忽略
     * 根据屏幕的大小。重新设置view的宽高
     *
     * @param view          将要改变高度或宽度的 view
     * @param widthPercent  宽度占整个屏幕宽度几份，0为wrap_content如，占屏幕1/2，则写2
     * @param heightPercent 高度占整个屏幕高度几份，0为wrap_content如，占屏幕1/2，则写2
     */

    fun setViewWidthOrHeight(view: View, widthPercent: Int, heightPercent: Int) {
        val params = view.layoutParams
        if (widthPercent != 0) {
            params.width = SwmScreenUtils.getScreenW() / widthPercent
        } else {
            params.width = ViewGroup.LayoutParams.WRAP_CONTENT
        }
        if (heightPercent != 0) {
            params.height = SwmScreenUtils.getScreenH() / heightPercent
        } else {
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        }
        view.layoutParams = params
    }

    /**
     * 正方形
     * 只能设置其中一个，另一个必须为0
     *
     * @param view
     * @param widthPercent  基于宽度的几分之几
     * @param heightPercent 基于高度的几分之几
     */
    fun setViewHeightAndWidth(view: View, widthPercent: Float, heightPercent: Int) {
        val params = view.layoutParams
        if (widthPercent != 0f) {
            params.width = (SwmScreenUtils.getScreenW() / widthPercent).toInt()
            params.height = params.width
        } else if (heightPercent != 0) {
            params.height = SwmScreenUtils.getScreenH() / heightPercent
            params.width = params.height
        }
        view.layoutParams = params
    }

    //    设置 view的 宽度
    fun setViewWidth(view: View, width: Int) {
        val params = view.layoutParams
        params.width = width
        view.layoutParams = params
    }

    //    设置 view的 高度
    fun setViewHeight(view: View, height: Int) {
        var params: ViewGroup.LayoutParams? = view.layoutParams
        if (params == null) {
            params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
        params.height = height
        view.layoutParams = params
    }

    //    设置 view MATCH_PARENT
    fun setViewMatch(view: View) {
        val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        view.layoutParams = layoutParams
    }


}
