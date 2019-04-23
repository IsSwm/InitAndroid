package com.mecm.initandroid.utils

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.provider.Settings
import android.support.v4.app.NotificationCompat
import android.support.v7.app.AlertDialog
import com.mecm.initandroid.R

class SwmNotificationUtils {


    companion object {
        /** 检查锁屏状态，如果锁屏先点亮屏幕
         *
         * @param content
         */
        fun checkLockAndShowNotification(context: Context, content: String,cls: Class<*>) {
            //管理锁屏的一个服务
            val km = context.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
            if (km.isKeyguardLocked) {//锁屏
                //获取电源管理器对象
                val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
                if (!pm.isScreenOn) {
                    @SuppressLint("InvalidWakeLockTag")
                    val wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP or PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "bright")
                    wl.acquire()  //点亮屏幕
                    wl.release()  //任务结束后释放
                }
                sendNotification(context, content, cls)
            } else {
                sendNotification(context, content, cls)
            }
        }

        /**
         * 发送通知
         *
         * @param content
         */
        fun sendNotification(context: Context, content: String,cls:Class<*>) {
            var intent = Intent()
            intent.setClass(context, cls)
            var pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            var notifyManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            var notification = NotificationCompat.Builder(context)
                    .setAutoCancel(true)
                    // 设置该通知优先级
                    .setPriority(Notification.PRIORITY_MAX)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("昵称")
                    .setContentText(content)
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    .setWhen(System.currentTimeMillis())
                    // 向通知添加声音、闪灯和振动效果
                    .setDefaults(Notification.DEFAULT_VIBRATE or Notification.DEFAULT_ALL or Notification.DEFAULT_SOUND)
                    .setContentIntent(pendingIntent)
                    .build()
            notifyManager.notify(1, notification)//id要保证唯一
        }

        /**
         * 检测是否开启通知
         * @param context
         */
        fun checkNotification(context: Context) {
            if (!isNotificationEnabled(context)) {
                AlertDialog.Builder(context).setTitle("温馨提示")
                        .setMessage("你还未开启系统通知，将影响消息的接收，要去开启吗？")
                        .setPositiveButton("确定") { _, _ ->
                            setNotification(context)
                        }
                        .setNegativeButton("取消") { _, _ -> }
                        .show()
            }
        }

        /**
         * 如果没有开启通知，跳转至设置界面
         *
         * @param context
         */
        @SuppressLint("ObsoleteSdkInt")
        private fun setNotification(context: Context) {
            var localIntent = Intent()
            //直接跳转到应用通知设置的代码：
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                localIntent.action = "android.settings.APP_NOTIFICATION_SETTINGS"
                localIntent.putExtra("app_package", context.packageName)
                localIntent.putExtra("app_uid", context.applicationInfo.uid)
            } else if (android.os.Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
                localIntent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                localIntent.addCategory(Intent.CATEGORY_DEFAULT)
                localIntent.data = Uri.parse("package:" + context.packageName)
            } else {
                //4.4以下没有从app跳转到应用通知设置页面的Action，可考虑跳转到应用详情页面
                localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                if (Build.VERSION.SDK_INT >= 9) {
                    localIntent.action = "android.settings.APPLICATION_DETAILS_SETTINGS"
                    localIntent.data = Uri.fromParts("package", context.packageName, null)
                } else if (Build.VERSION.SDK_INT <= 8) {
                    localIntent.action = Intent.ACTION_VIEW
                    localIntent.setClassName("com.android.settings", "com.android.setting.InstalledAppDetails")
                    localIntent.putExtra("com.android.settings.ApplicationPkgName", context.packageName)
                }
            }
            context.startActivity(localIntent)
        }

        /**
         * 获取通知权限,检测是否开启了系统通知
         *
         * @param context
         */
        private fun isNotificationEnabled(context: Context): Boolean {
            val CHECK_OP_NO_THROW = "checkOpNoThrow"
            val OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION"
            val mAppOps = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
            val appInfo = context.applicationInfo
            val pkg = context.applicationContext.packageName
            val uid = appInfo.uid
            val appOpsClass: Class<*>
            try {
                appOpsClass = Class.forName(AppOpsManager::class.java.name)
                val checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE,
                        String::class.java)
                val opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION)
                val value = opPostNotificationValue.get(Integer::class) as Int
                return (checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) as Int == AppOpsManager.MODE_ALLOWED)

            } catch (e: Exception) {
                e.printStackTrace()
            }
            return false
        }

    }


}
