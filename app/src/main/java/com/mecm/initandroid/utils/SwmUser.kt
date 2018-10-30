package com.jjj.jiatingfuwuqkl.utils

import android.content.Context
import com.mecm.initandroid.bean.MyConstants
import com.mecm.initandroid.utils.SwmCacheUtils
import com.mecm.initandroid.utils.SwmStringUtils

/**
 * Created by Administrator on 2018/7/23.
 */
class SwmUser {

    companion object {

        fun putUser(context: Context, result: String) {
            val swmCacheUtils = SwmCacheUtils.get(context, MyConstants.CACHE_USER)
            swmCacheUtils.put(MyConstants.CACHE_USER, result)
        }

        fun clearUser(context: Context) {
            val swmCacheUtils = SwmCacheUtils.get(context, MyConstants.CACHE_USER)
            swmCacheUtils.remove(MyConstants.CACHE_USER)
        }

        fun isNotLogin(context: Context): Boolean {
            return SwmStringUtils.isEmpty(getUser(context))
        }

        fun getUser(context: Context): String? {
            val cache = SwmCacheUtils.get(context, MyConstants.CACHE_USER)
            return if (SwmStringUtils.isEmpty(cache.getAsString(MyConstants.CACHE_USER))) {
//                val dialog = AlertDialog.Builder(AppContext.context!!)
//                dialog.setTitle("请先登录")
//                dialog.setPositiveButton("登录", { _, _ ->
//                    val intent = Intent(AppContext.context!!, LoginActivity::class.java)
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                    AppContext.context!!.startActivity(intent)
//                })
//                dialog.setNegativeButton("取消", { dialogInterface, i ->
//                    dialogInterface.dismiss()
//                    dialogInterface.cancel()
//                })
//                dialog.show()
                null
            } else {
                cache.getAsString(MyConstants.CACHE_USER)
                /* if (MyConstants.isOnline) {
                    cache.getAsString(MyConstants.CACHE_USER)
                } else {
                    "j2pZjE2rweSj5F5lVy/DJQ=="
                }*/
            }
        }
    }

}