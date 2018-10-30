//package com.mecm.initandroid.utils
//
//import android.app.Activity
//import android.content.Context
//import com.amap.api.location.AMapLocation
//import com.amap.api.location.AMapLocationClient
//import com.amap.api.location.AMapLocationClientOption
//import com.amap.api.location.AMapLocationListener
//import com.hjq.permissions.OnPermission
//import com.hjq.permissions.Permission
//import com.hjq.permissions.XXPermissions
//
///**
// *  高德地图 定位工具
// */
//class SwmLocationUtils {
//
//    private var locationClient: AMapLocationClient? = null
//    private var locationOption: AMapLocationClientOption? = null
//
//    private lateinit var context: Context
//
//    constructor(context: Context) {
//        this.context = context
//        initLocation(context)
//    }
//
//    /**
//     * 初始化定位
//     *
//     * @since 2.8.0
//     * @author hongming.wang
//     */
//    private fun initLocation(context: Context) {
//        //初始化client
//        locationClient = AMapLocationClient(context)
//        locationOption = getDefaultOption()
//        //设置定位参数
//        locationClient!!.setLocationOption(locationOption)
//        // 设置定位监听
//        locationClient!!.setLocationListener(locationListener)
//    }
//
//    fun startLocation(activity: Activity) {
//        SwmLogUtils.e(XXPermissions.isHasPermission(activity, Permission.Group.LOCATION))
//
//        if (!XXPermissions.isHasPermission(activity, Permission.Group.LOCATION)) {
//            XXPermissions.with(activity)
//                    .constantRequest() //可设置被拒绝后继续申请，直到用户授权或者永久拒绝
////                    .permission(Permission.SYSTEM_ALERT_WINDOW) //支持请求6.0悬浮窗权限8.0请求安装权限
//                    .permission(Permission.Group.LOCATION) //不指定权限则自动获取清单中的危险权限
//                    .request(object : OnPermission {
//                        override fun hasPermission(granted: List<String>, isAll: Boolean) {
//                            if (!isAll) {
//                                SwmToastUtils.showSingletonToast("请全部授权")
//                            }
//                            // 启动定位
//                            locationClient!!.startLocation()
//                        }
//                        override fun noPermission(denied: List<String>, quick: Boolean) {
//                        }
//                    })
//        }
//
//        // 启动定位
//        locationClient!!.startLocation()
//    }
//
//    /** 停止定位 */
//    fun stopLocation() {
//        if (locationClient != null) {
//            // 停止定位
//            locationClient!!.stopLocation()
//        }
//    }
//
//    /** 销毁定位 */
//    fun destroyLocation() {
//        if (null != locationClient) {
//            /**
//             * 如果AMapLocationClient是在当前Activity实例化的，
//             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
//             */
//            locationClient!!.onDestroy()
//            locationClient = null
//            locationOption = null
//        }
//    }
//
//
//    /**
//     * 默认的定位参数
//     * @since 2.8.0
//     * @author hongming.wang
//     */
//    private fun getDefaultOption(): AMapLocationClientOption {
//        val mOption = AMapLocationClientOption()
//        mOption.locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
//        mOption.httpTimeOut = 30000//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
//        mOption.interval = 2000//可选，设置定位间隔。默认为2秒
//        mOption.isNeedAddress = true//可选，设置是否返回逆地理地址信息。默认是true
//        mOption.isOnceLocation = false//可选，设置是否单次定位。默认是false
//        mOption.isOnceLocationLatest = false//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
//        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP)//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
//        mOption.isSensorEnable = false//可选，设置是否使用传感器。默认是false
//        mOption.isWifiScan = true //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
//        mOption.isLocationCacheEnable = true //可选，设置是否使用缓存定位，默认为true
//        mOption.geoLanguage = AMapLocationClientOption.GeoLanguage.DEFAULT//可选，设置逆地理信息的语言，默认值为默认语言（根据所在地区选择语言）
//        return mOption
//    }
//
//    var mLocationListener: MLocationListener? = null
//
//    interface MLocationListener {
//        fun location(location: AMapLocation, address: String)
//    }
//
//    /**
//     * 定位监听
//     */
//    private var locationListener: AMapLocationListener = AMapLocationListener { location ->
//        if (mLocationListener != null) {
//            val address = location.province + location.city + location.district + location.address
//            mLocationListener!!.location(location, address)
//            stopLocation()
//        }
//        if (null != location) {
////            val sb = StringBuffer()
////            //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
////            if (location.errorCode == 0) {
////                sb.append("定位成功" + "\n")
////                sb.append("定位类型: " + location.locationType + "\n")
////                sb.append("经    度    : " + location.longitude + "\n")
////                sb.append("纬    度    : " + location.latitude + "\n")
////                sb.append("精    度    : " + location.accuracy + "米" + "\n")
////                sb.append("提供者    : " + location.provider + "\n")
////
////                sb.append("速    度    : " + location.speed + "米/秒" + "\n")
////                sb.append("角    度    : " + location.bearing + "\n")
////                // 获取当前提供定位服务的卫星个数
////                sb.append("星    数    : " + location.satellites + "\n")
////                sb.append("国    家    : " + location.country + "\n")
////                sb.append("省            : " + location.province + "\n")
////                sb.append("市            : " + location.city + "\n")
////                sb.append("城市编码 : " + location.cityCode + "\n")
////                sb.append("区            : " + location.district + "\n")
////                sb.append("区域 码   : " + location.adCode + "\n")
////                sb.append("地    址    : " + location.address + "\n")
////                sb.append("兴趣点    : " + location.poiName + "\n")
////            } else {
////                //定位失败
////                sb.append("定位失败" + "\n")
////                sb.append("错误码:" + location.errorCode + "\n")
////                sb.append("错误信息:" + location.errorInfo + "\n")
////                sb.append("错误描述:" + location.locationDetail + "\n")
////            }
////            sb.append("***定位质量报告***").append("\n")
////            sb.append("* WIFI开关：").append(if (location.locationQualityReport.isWifiAble) "开启" else "关闭").append("\n")
////            sb.append("* GPS星数：").append(location.locationQualityReport.gpsSatellites).append("\n")
////            sb.append("* 网络类型：" + location.locationQualityReport.networkType).append("\n")
////            sb.append("* 网络耗时：" + location.locationQualityReport.netUseTime).append("\n")
////            sb.append("****************").append("\n")
////            //解析定位结果，
////            val result = sb.toString()
//        } else {
//            SwmToastUtils.showSingletonToast("定位失败，loc is null")
//        }
//    }
//
//
//}
//
