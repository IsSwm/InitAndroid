package com.mecm.initandroid.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Build
import android.os.SystemClock
import android.provider.Settings
import android.telephony.TelephonyManager
import com.mecm.initandroid.AppContext
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader

/**
 * 手机信息 & MAC地址 & 开机时间
 */
object SwmSystemUtils {
    private val TAG = SwmSystemUtils::class.java.simpleName
    //Ethernet Mac Address
    private val ETH0_MAC_ADDRESS = "/sys/class/net/eth0/address"

    //    获取 APP 名称
    val appName: String
        @Throws(PackageManager.NameNotFoundException::class)
        get() {
            val appName: String
            val packageManager = packageManager
            val applicationInfo = appInfo
            appName = packageManager.getApplicationLabel(applicationInfo) as String
            return appName

        }

    //    获取 APP 版本号
    val appVersion: String
        @Throws(PackageManager.NameNotFoundException::class)
        get() = packageManager.getPackageInfo(appInfo.packageName, 0).versionName

    //    获取APP 信息
    private val appInfo: ApplicationInfo
        @Throws(PackageManager.NameNotFoundException::class)
        get() = packageManager.getApplicationInfo(AppContext.context!!.packageName, 0)

    //    获取包管理者
    private val packageManager: PackageManager
        get() = AppContext.context!!.packageManager

    //    获取设备厂商
    val brand: String
        get() = Build.BRAND
    //    设备名称
    val model: String
        get() = Build.MODEL


    /** 判断手机是否root，不弹出root请求框  */
    val isRoot: Boolean
        get() {
            val binPath = "/system/bin/su"
            val xBinPath = "/system/xbin/su"
            if (File(binPath).exists() && isExecutable(binPath))
                return true
            return File(xBinPath).exists() && isExecutable(xBinPath)
        }

    /**
     * 获取 开机时间
     */
    val bootTimeString: String
        get() {
            val ut = SystemClock.elapsedRealtime() / 1000
            val h = (ut / 3600).toInt()
            val m = (ut / 60 % 60).toInt()
            SwmLogUtils.i(h.toString() + ":" + m)
            return h.toString() + ":" + m
        }

    /**
     * 获取 Wifi MAC 地址
     * <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"></uses-permission>
     */
    @Deprecated("", ReplaceWith("getWifiMacAddress(context)", "com.jjj.jiatingfuwuqkl.utils.SwmSystemUtils.getWifiMacAddress"))
    fun getMacAddress(context: Context): String {
        return getWifiMacAddress(context)
    }

    @SuppressLint("MissingPermission")
     /**
     * 获取 Wifi MAC 地址
     * <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"></uses-permission>
     */
    fun getWifiMacAddress(context: Context): String {
        //wifi mac地址
        val wifi = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val info = wifi.connectionInfo
        return info.macAddress
    }

    @SuppressLint("MissingPermission")
//   获取网络状态
    fun getAPNType(context: Context): String {
        //结果返回值
        var netType = "nono_connect"
        //获取手机所有连接管理对象
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        //获取NetworkInfo对象
        val networkInfo = manager.activeNetworkInfo ?: return netType
        //NetworkInfo对象为空 则代表没有网络
        //否则 NetworkInfo对象不为空 则获取该networkInfo的类型
        val nType = networkInfo.type
        if (nType == ConnectivityManager.TYPE_WIFI) {
            //WIFI
            netType = "wifi"
        } else if (nType == ConnectivityManager.TYPE_MOBILE) {
            val nSubType = networkInfo.subtype
            val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            //4G
            if (nSubType == TelephonyManager.NETWORK_TYPE_LTE && !telephonyManager.isNetworkRoaming) {
                netType = "4G"
            } else if (nSubType == TelephonyManager.NETWORK_TYPE_UMTS || nSubType == TelephonyManager.NETWORK_TYPE_HSDPA || nSubType == TelephonyManager.NETWORK_TYPE_EVDO_0 && !telephonyManager.isNetworkRoaming) {
                netType = "3G"
                //2G 移动和联通的2G为GPRS或EGDE，电信的2G为CDMA
            } else if (nSubType == TelephonyManager.NETWORK_TYPE_GPRS || nSubType == TelephonyManager.NETWORK_TYPE_EDGE || nSubType == TelephonyManager.NETWORK_TYPE_CDMA && !telephonyManager.isNetworkRoaming) {
                netType = "2G"
            } else {
                netType = "2G"
            }
        }
        return netType
    }

    private fun isExecutable(filePath: String): Boolean {
        var p: Process? = null
        try {
            p = Runtime.getRuntime().exec("ls -l " + filePath)
            // 获取返回内容
            val `in` = BufferedReader(InputStreamReader(
                    p!!.inputStream))
            val str = `in`.readLine()
            if (str != null && str.length >= 4) {
                val flag = str[3]
                if (flag == 's' || flag == 'x')
                    return true
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (p != null) {
                p.destroy()
            }
        }
        return false
    }

    @SuppressLint("HardwareIds")
     /**
     * 获取 ANDROID_ID
     */
    fun getAndroidId(context: Context): String {
        val androidId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        SwmLogUtils.i("ANDROID_ID ：" + androidId)
        return androidId
    }

}