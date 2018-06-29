package com.jjj.moneybag.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.view.View

open class BaseActivity : AppCompatActivity() {
    // 设置头部箭头返回事件
    fun setBackFinish(view: View) {
        view.visibility = View.VISIBLE
        view.setOnClickListener({
            finish()
        })
    }

    // 设置头部按钮的点击事件
    fun setBtnClick(view: View, clickListener: View.OnClickListener) {
        view.visibility = View.VISIBLE
        view.setOnClickListener(clickListener)
    }

    fun mStartActivity(packageContext: Context, cls:Class<*> ){
        packageContext.startActivity(Intent(packageContext, cls))
    }

}
