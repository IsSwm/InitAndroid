package com.mecm.initandroid.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri

/**
 * 跳转拨打界面
 */

object SwmCallPhoneUtils {


    //    跳转到 拨打电话界面，并且传递电话号码。
    fun toCallWithPhone(activity: Activity, phone: String) {
        val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone))//跳转到拨号界面，同时传递电话号码
        activity.startActivity(dialIntent)
    }

    //    跳转到拨号界面
    fun toCallPhone(activity: Activity) {
        val dialIntent = Intent(Intent.ACTION_CALL_BUTTON)//跳转到拨号界面
        activity.startActivity(dialIntent)
    }


}
