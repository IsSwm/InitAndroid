package com.mecm.initandroid.views

import android.animation.ValueAnimator
import android.app.Activity
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import com.mecm.initandroid.utils.SwmScreenUtils

/**
 *  不知道在哪看的，做了一些改动。使用RecyclerView作为PopupWindow的主控件
 *  只需实现Adapter即可使用
 *  PS: 需要导入 RecyclerView包
 */
class BasePopupWindow(private val context: Context) : PopupWindow() {
    // 透明度
    var mShowAlpha = 0.88f
    private var mBackgroundDrawable: Drawable? = null

    init {
        initBasePopupWindow()
    }

    /**
     * 设置 popupWindow的宽高
     * 如果  参数 等于 1.0 为 MATCH_PARENT
     * 如果  参数 等于 0.0 为默认值WRAP_CONTENT
     * @param widthPercent  占整个屏幕的多少宽度
     * @param heightPercent  占整个屏幕的多少高度
     */
    fun setWidthAndHeight(widthPercent: Double, heightPercent: Double) {
        if (widthPercent != 0.0) {
            this.width = (SwmScreenUtils.getScreenW() * widthPercent).toInt()
        }
        if (heightPercent != 0.0) {
            this.height = (SwmScreenUtils.getScreenH() * heightPercent).toInt()
        }
    }

    /**
     *  重写 设置外部触摸是否可以关闭
     */
    override fun setOutsideTouchable(touchable: Boolean) {
        super.setOutsideTouchable(touchable)
        if (touchable) {
            if (mBackgroundDrawable == null) {
                mBackgroundDrawable = ColorDrawable(0x00000000)
            }
            super.setBackgroundDrawable(mBackgroundDrawable)
        } else {
            super.setBackgroundDrawable(null)
        }
    }

    override fun setBackgroundDrawable(background: Drawable) {
        mBackgroundDrawable = background
        isOutsideTouchable = isOutsideTouchable
    }

    /**
     * 初始化BasePopupWindow的一些信息
     */
    private fun initBasePopupWindow() {
        animationStyle = android.R.style.Animation_Dialog
        height = ViewGroup.LayoutParams.WRAP_CONTENT
        width = ViewGroup.LayoutParams.WRAP_CONTENT
        isOutsideTouchable = true  //默认设置outside点击无响应
        isFocusable = true
    }

    fun setView(adapter:Adapter<*>) {
        val recyclerView = RecyclerView(context)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        //       将view添加到  popup里面
        contentView = recyclerView
        isFocusable = true
    }


    override fun showAtLocation(parent: View, gravity: Int, x: Int, y: Int) {
        super.showAtLocation(parent, gravity, x, y)
        showAnimator().start()
    }

    override fun showAsDropDown(anchor: View) {
        super.showAsDropDown(anchor)
        showAnimator().start()
    }

    override fun showAsDropDown(anchor: View, xoff: Int, yoff: Int) {
        super.showAsDropDown(anchor, xoff, yoff)
        showAnimator().start()
    }

    override fun showAsDropDown(anchor: View, xoff: Int, yoff: Int, gravity: Int) {
        super.showAsDropDown(anchor, xoff, yoff, gravity)
        showAnimator().start()
    }

    override fun dismiss() {
        super.dismiss()
        dismissAnimator().start()
    }

    /**
     * 窗口显示，窗口背景透明度渐变动画
     */
    private fun showAnimator(): ValueAnimator {
        val animator = ValueAnimator.ofFloat(1.0f, mShowAlpha)
        animator.addUpdateListener { animation ->
            val alpha = animation.animatedValue as Float
            setWindowBackgroundAlpha(alpha)
        }
        animator.duration = 360
        return animator
    }

    /**
     * 窗口隐藏，窗口背景透明度渐变动画
     */
    private fun dismissAnimator(): ValueAnimator {
        val animator = ValueAnimator.ofFloat(mShowAlpha, 1.0f)
        animator.addUpdateListener { animation ->
            val alpha = animation.animatedValue as Float
            setWindowBackgroundAlpha(alpha)
        }
        animator.duration = 320
        return animator
    }

    /**
     * 为窗体 添加返回键响应事件
     */
    fun addKeyListener(isCanBack:Boolean) {
        if (contentView != null) {
            if (isCanBack) {
                contentView.setOnKeyListener(onKeyBackListener)
            }else{
                contentView.setOnKeyListener(null)

            }
        }
    }
    private val onKeyBackListener = View.OnKeyListener { view, keyCode, event ->
        when (keyCode) {
            KeyEvent.KEYCODE_BACK -> {
                dismiss()
                return@OnKeyListener true
            }
            else -> {
            }
        }
        false
    }
    /**
     * 控制窗口背景的不透明度
     */
    private fun setWindowBackgroundAlpha(alpha: Float) {
        val window = (context as Activity).window
        val layoutParams = window.attributes
        layoutParams.alpha = alpha
        window.attributes = layoutParams
    }
}
