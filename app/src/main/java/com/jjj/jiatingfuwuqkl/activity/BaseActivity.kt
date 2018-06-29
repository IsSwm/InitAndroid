package com.jjj.moneybag.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.global_header.*

open class BaseActivity : AppCompatActivity() {
    // 设置头部 箭头返回事件
    fun setBackBtnFinish() {
        header_back.visibility = View.VISIBLE
        header_back.setOnClickListener({
            finish()
        })
    }
    // 设置头部 标题内容
    fun setTitle(text:String){
        header_title.text = text
    }

    // 设置头部 按钮点击事件
    fun setBtnClick(clickListener: View.OnClickListener) {
        header_btn.visibility = View.VISIBLE
        header_btn.setOnClickListener(clickListener)
    }
    // 启动activity 使用 mStartActivity(context,*::class.java)
    fun mStartActivity(packageContext: Context, cls:Class<*> ){
        packageContext.startActivity(Intent(packageContext, cls))
    }

}
