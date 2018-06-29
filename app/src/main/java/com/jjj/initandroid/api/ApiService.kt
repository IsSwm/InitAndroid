package com.jjj.initandroid.api


import com.jjj.initandroid.bean.APIConstants
import com.jjj.initandroid.entity.BaseData

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.POST

/**
 * Created by 九贡 on 2017/10/8.
 */

interface ApiService {

    //    初始化 任务列表
    //    @FormUrlEncoded
    //    实体类
    @POST(APIConstants.GET_CODE)
    fun getTaskList(@Field("userId") userId: String): Observable<BaseData>

}
