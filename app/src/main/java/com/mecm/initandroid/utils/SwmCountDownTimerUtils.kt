package com.mecm.initandroid.utils

import android.graphics.Color
import android.os.CountDownTimer
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import com.mecm.initandroid.R

/**
 * 手机 验证码计时器
 */
class SwmCountDownTimerUtils
/**
 * @param textView          The TextView
 * @param millisInFuture   总计时时间 毫秒单位 The number of millis in the future from the call
 * to [.start] until the countdown is done and [.onFinish]
 * is called.
 * @param countDownInterval  倒计时终止时间 The interval along the way to receiver
 * [.onTick] callbacks.
 */
(private val mTextView: TextView, millisInFuture: Long, countDownInterval: Long) : CountDownTimer(millisInFuture, countDownInterval) {

    override fun onTick(millisUntilFinished: Long) {
        mTextView.isClickable = false //设置不可点击
        mTextView.text = (millisUntilFinished / 1000).toString() + "秒后重试"  //设置倒计时时间
        mTextView.setBackgroundResource(R.drawable.bg_identify_code_press) //设置按钮为灰色，这时是不能点击的

        /**
         * 超链接 URLSpan
         * 文字背景颜色 BackgroundColorSpan
         * 文字颜色 ForegroundColorSpan
         * 字体大小 AbsoluteSizeSpan
         * 粗体、斜体 StyleSpan
         * 删除线 StrikethroughSpan
         * 下划线 UnderlineSpan
         * 图片 ImageSpan
         * http://blog.csdn.net/ah200614435/article/details/7914459
         */
        val spannableString = SpannableString(mTextView.text.toString())  //获取按钮上的文字
        val span = ForegroundColorSpan(Color.RED)
        /**
         * public void setSpan(Object what, int start, int end, int flags) {
         * 主要是start跟end，start是起始位置,无论中英文，都算一个。
         * 从0开始计算起。end是结束位置，所以处理的文字，包含开始位置，但不包含结束位置。
         */
        spannableString.setSpan(span, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)//将倒计时的时间设置为红色
        mTextView.text = spannableString
    }

    override fun onFinish() {
        mTextView.setText(R.string.login_phone_send)
        mTextView.isClickable = true//重新获得点击
        mTextView.setBackgroundResource(R.drawable.shape_btn_register)  //还原背景色
    }
}  