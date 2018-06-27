package com.jjj.jiatingfuwuqkl.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * 时间转换工具
 * 获取日期等
 */

object SwmTimeUtils {
    /**
     * 时间戳转为时间(年月日，时分秒)

     * @param cc_time 时间戳
     * *
     * @param format 时间格式.例如："yyyy/MM/dd HH:mm:ss"
     * *
     * @return
     */
    fun getStrTime(cc_time: String, format: String?): String {
        var format = format
        var re_StrTime: String? = null
        if (format == null) {
            format = "yyyy/MM/dd"
        }
        //同理也可以转为其它样式的时间格式.例如："yyyy/MM/dd HH:mm"
        val sdf = SimpleDateFormat(format)
        // 例如：cc_time=1291778220
        val lcc_time = java.lang.Long.valueOf(cc_time)!!
        re_StrTime = sdf.format(Date(lcc_time * 1000L))

        return re_StrTime
    }

    /**
     * 时间转换为时间戳

     * @param timeStr 时间 例如: 2017-10-05 16:00:00
     * *
     * @param format  时间对应格式  例如: yyyy-MM-dd HH:mm:ss
     * *
     * @return
     */
    fun getTimeStamp(timeStr: String, format: String): Long {
        val simpleDateFormat = SimpleDateFormat(format)
        var date: Date? = null
        try {
            date = simpleDateFormat.parse(timeStr)
            val timeStamp = date!!.time
            return timeStamp
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return 0
    }

    /**
     * 将给定的 日期转换为 yyyy年MM月dd日HH时mm分ss秒
     * @param time
     * *
     * @return
     */
    fun timestamp(time: Long): String {
        val sdr = SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒")
        val date = Date(time)
        val format = sdr.format(date)
        return format
    }


    /**
     * 获取 月 和 日
     * @param time
     * @return
     */
    fun getMonDay(time: Long): String {
        val sdr = SimpleDateFormat("MM月dd日")
        val date = Date(time)
        val format = sdr.format(date)
        return format
    }

    /**
     * 获取 当前的 日期 几号
     * @return
     */
    val nowDay: Int
        get() {
            val calendar = Calendar.getInstance()
            return calendar.get(Calendar.DAY_OF_MONTH)
        }

    /**
     * 获取 周几
     * @param time
     * *
     * @return
     */
    /**
     * 获取星期几

     * @return
     */
    fun getWeek(date: Date): String {
        var mydate = 0
        var week: String? = null
        try {
            val cd = Calendar.getInstance()
            cd.time = date
            mydate = cd.get(Calendar.DAY_OF_WEEK)
            // 获取指定日期转换成星期几
        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (mydate == 1) {
            week = "星期日"
        } else if (mydate == 2) {
            week = "星期一"
        } else if (mydate == 3) {
            week = "星期二"
        } else if (mydate == 4) {
            week = "星期三"
        } else if (mydate == 5) {
            week = "星期四"
        } else if (mydate == 6) {
            week = "星期五"
        } else if (mydate == 7) {
            week = "星期六"
        }
        return week!!

    }


}