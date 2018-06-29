package com.jjj.initandroid.utils

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.LayoutRes
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jjj.initandroid.AppContext


/**
 * Created by IsSwm on 2016/8/25.
 */
class SwmUIUtils {

    //     设置添加屏幕的背景透明度
    fun backgroundAlpha(bgAlpha: Float, activity: Activity) {
        val lp = activity.window.attributes
        lp.alpha = bgAlpha //0.0-1.0
        activity.window.attributes = lp
    }

    companion object {

        //     * 获取上下文
        val context: Context
            get() = AppContext.context!!

        //    -----------------  自定义方法  -----------------


        //    -----------------  加载资源文件  -------------------

        /**
         * 获取类型数组  自定义属性信息

         * @param attr style.xml 中的 declare-styleable 的自定义属性 数组 R.styleable.custom_name
         */
        fun getTypedArray(context: Context, attributeSet: AttributeSet, attr: IntArray): TypedArray {
            return context.obtainStyledAttributes(attributeSet, attr, 0, 0)
        }


        //    获取 图片资源
        fun getDrawable(id: Int): Drawable {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                return context.resources.getDrawable(id, null)
            } else {
                return context.resources.getDrawable(id)
            }
        }

        //    获取 颜色
        fun getColor(id: Int): Int {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return context.resources.getColor(id, null)
            } else {
                return context.resources.getColor(id)
            }
        }

        //    根据Id 获取 颜色的 状态选择器
        fun getColorStateList(id: Int): ColorStateList {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return context.resources.getColorStateList(id, null)
            } else {
                return context.resources.getColorStateList(id)
            }
        }

        //    获取尺寸
        fun getDimen(id: Int): Int {
            return context.resources.getDimensionPixelSize(id)
        }

        //    dp 和 px 之间的转换
        fun dp2px(dip: Float): Int {
            val density = context.resources.displayMetrics.density
            return (dip * density + 0.5f).toInt()
        }

        fun px2dp(px: Float): Int {
            val density = context.resources.displayMetrics.density
            return (px / density + 0.5f).toInt()
        }

        /**
         * sp2px
         * @param spVal
         * *
         * @return
         */
        fun sp2px(spVal: Int): Int {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                    spVal.toFloat(), context.resources.displayMetrics).toInt()
        }

        //    -----------------  加载布局文件  -------------------
        fun inflate(@LayoutRes resLayoutId: Int, root: ViewGroup, attachToRoot: Boolean): View {
            return LayoutInflater.from(context).inflate(resLayoutId, root, attachToRoot)
        }
    }


}
