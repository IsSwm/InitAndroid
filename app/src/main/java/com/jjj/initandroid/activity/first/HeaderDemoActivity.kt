package com.jjj.initandroid.activity.first

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.jjj.initandroid.R
import com.jjj.initandroid.utils.SwmToastUtils
import com.jjj.moneybag.activity.BaseActivity

class HeaderDemoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_header_demo)
        // 设置顶部内容
        setTitle("顶部Demo演示")
        // 退出activity
        setBackBtnFinish()
        // 右侧按钮事件
        setBtnClick(View.OnClickListener {
            SwmToastUtils.showToast("我是顶部Demo演示")
        })

    }
}
