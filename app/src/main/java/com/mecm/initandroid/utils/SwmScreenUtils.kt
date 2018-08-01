package com.mecm.initandroid.utils

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.WindowManager

import com.mecm.initandroid.AppContext

/**
 * 获取手机屏幕信息的工具
 */
object SwmScreenUtils {
    private var screenW: Int = 0
    private var screenH: Int = 0


    //    获取通知栏的高度
    //            getDimensionPixelSize 四舍五入  offset  直接舍去  getDimension  原来的摸样精确度高
    val stasusBarHeight: Float
        get() {
            val identifier = AppContext.context!!.resources.getIdentifier("status_bar_height", "dimen", "android")
            if (identifier > 0) {
                return AppContext.context!!.resources.getDimensionPixelSize(identifier).toFloat()
            }
            throw RuntimeException("获取不到通知栏的高度")
        }

    fun getScreenW(): Int {
        if (screenW == 0) {
            initScreen()
        }
        return screenW
    }

    fun getScreenH(): Int {
        if (screenH == 0) {
            initScreen()
        }
        return screenH
    }

    private fun initScreen() {
        val wm = AppContext.context!!
                .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        screenW = outMetrics.widthPixels
        screenH = outMetrics.heightPixels
    }

    /**
     * 获取当前屏幕截图，包含状态栏
     *
     * @return
     */
    fun snapShotWithStatusBar(activity: Activity): Bitmap? {
        val view = activity.window.decorView
        view.isDrawingCacheEnabled = true
        view.buildDrawingCache()
        val bmp = view.drawingCache
        val width = getScreenW()
        val height = getScreenH()
        var bp: Bitmap? = null
        bp = Bitmap.createBitmap(bmp, 0, 0, width, height)
        view.destroyDrawingCache()
        return bp

    }

    /**
     * 获取当前屏幕截图，不包含状态栏
     *
     * @param activity
     * @return
     */
    fun snapShotWithoutStatusBar(activity: Activity): Bitmap? {
        val view = activity.window.decorView
        view.isDrawingCacheEnabled = true
        view.buildDrawingCache()
        val bmp = view.drawingCache
        val frame = Rect()
        activity.window.decorView.getWindowVisibleDisplayFrame(frame)
        val statusBarHeight = frame.top
        val width = getScreenW()
        val height = getScreenH()
        var bp: Bitmap? = null
        bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height - statusBarHeight)
        view.destroyDrawingCache()
        return bp

    }
}
