package com.mecm.initandroid.utils

import android.widget.TextView

object SwmCodeUtils {
    /**
     * 判断字符串是否有值，如果为null或者是空字符串或者只有空格或者为"null"字符串，则返回true，否则则返回false
     */
    fun sendCode(timerVIew: TextView, registerPhone: TextView, type: String) {
        val regPhone = SwmTextUtils.getText(registerPhone)
        if (SwmRegUtils.isMobileNOToast(regPhone)) {
            SwmCountDownTimerUtils(timerVIew, 60000, 1000).start()
//            SwmRxHttpUtils(object : SwmRxHttpUtils.SwmRequestComListener {
//                override fun onNextError() {
//                }
//
//                override fun onSubscribe(d: Disposable) {
//                }
//
//                override fun onNext(baseData: BaseData) {
//
//                }
//
//                override fun onComplete() {
//                }
//            }).getRegCode(regPhone,type)
        }
    }
}