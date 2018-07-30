package com.jjj.initandroid.api


import com.jjj.initandroid.bean.APIConstants
import com.jjj.initandroid.entity.BaseData

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by 九贡 on 2017/10/8.
 */

interface ApiService {

    //    初始化 任务列表
    //    @FormUrlEncoded
    //    实体类
    @POST(APIConstants.GET_CODE)
    fun getTaskList(@Field("userId") userId: String): Observable<BaseData>



    // 获取最新的版本
    @Streaming //大文件时要加不然会OOM
    @GET
    fun downloadFile(@Url fileUrl: String): Call<ResponseBody>

}
