package com.mecm.moneybag.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.jjj.jiatingfuwuqkl.utils.SwmUser
import kotlinx.android.synthetic.main.global_header.*
import org.json.JSONObject

open class BaseActivity : AppCompatActivity() {
    // 设置头部 箭头返回事件
    fun setBackBtnFinish() {
        header_back.visibility = View.VISIBLE
        header_back.setOnClickListener {
            finish()
        }
    }

    // 设置头部 标题内容
    fun setTitle(text: String) {
        header_title.text = text
    }

    // 设置头部 按钮点击事件
    fun setBtnClick(text: String, clickListener: View.OnClickListener) {
        header_text.visibility = View.VISIBLE
        header_text.text = text
        header_text.setOnClickListener(clickListener)
    }

    // 设置头部 图片按钮点击事件
    fun setIvClick(clickListener: View.OnClickListener) {
        header_iv.visibility = View.VISIBLE
        header_iv.setOnClickListener(clickListener)
    }

    // 启动activity 使用 mStartActivity(context,*::class.java)
    fun mStartActivity(packageContext: Context, cls: Class<*>) {
        packageContext.startActivity(Intent(packageContext, cls))
    }

    // 启动activity 使用 mStartActivity(context,*::class.java)
    fun mStartActivity(cls: Class<*>) {
        this.startActivity(Intent(this, cls))
    }

    // 启动activity 使用 mStartActivity(context,*::class.java)
    fun mStartActivityWithClear(cls: Class<*>) {
        val intent = Intent(this, cls)
//        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }


    // 启动activity 使用 mStartActivity(context,*::class.java)
    fun mStartActivity(cls: Class<*>, url: String, param: JSONObject) {
        param.put("token", SwmUser.getUser(this))
        val intent = Intent(this, cls)
        intent.putExtra("url", url)
        intent.putExtra("params", param.toString())
        this.startActivity(intent)
    }

}
