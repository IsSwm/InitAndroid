package com.mecm.initandroid.activity.first

import android.os.Bundle
import android.view.View
import com.mecm.initandroid.R
import com.mecm.initandroid.utils.SwmToastUtils
import com.mecm.moneybag.activity.BaseActivity

class HeaderDemoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_header_demo)
        // 设置顶部内容
        setTitle("顶部Demo演示")
        // 退出activity
        setBackBtnFinish()
        // 右侧按钮事件
        setBtnClick("保存",View.OnClickListener {
            SwmToastUtils.showToast("我是顶部Demo演示")
        })

    }
}
