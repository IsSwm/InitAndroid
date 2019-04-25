package com.mecm.initandroid.utils

import com.mecm.initandroid.AppContext
import com.mecm.initandroid.entity.BaseData
import com.mecm.moneybag.utils.SwmFileUtil

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 网络请求 基于 retrofit 与 rxjava
 */
class SwmRxHttpUtils {

    private val mBaseDataObserver: Observer<BaseData>

    constructor()

    /**
     * 请求完成做的事情
     * @param swmRequestComListener
     */
    constructor(swmRequestComListener: SwmRequestComListener) {
        this.swmRequestComListener = swmRequestComListener
    }

    init {
        mBaseDataObserver = object : Observer<BaseData> {

            private var mDisposable: Disposable? = null
            /**
             * 可以在此做一些初始化操作，应该是这样的。
             */
            override fun onSubscribe(d: Disposable) {
                mDisposable = d
                if (swmRequestOnSubscribeListener != null) swmRequestOnSubscribeListener!!.onSubscribe(d)
            }

            /**
             * 请求返回的数据 封装的基类
             *  status 判断可根据你亲爱的服务器大神的返回~
             */
            override fun onNext(baseData: BaseData) {
                //    如果 服务器 返回的  请求 状态码 等于success 代码有数据
                if ("success" == baseData.status) {
                    if (swmRequestComListener != null) swmRequestComListener!!.onNext(baseData)

                    if (swmRequestOnNextListener != null) swmRequestOnNextListener!!.onNext(baseData)
                } else {
                    if (swmRequestOnNextErrorListener != null) swmRequestOnNextErrorListener!!.onNextError(baseData)

                    if (swmRequestComListener != null) swmRequestComListener!!.onNextError(baseData)
                    //   显示服务器的错误信息
                    SwmToastUtils.showToast(baseData.message)
                }
            }

            /**
             * 请求失败返回的信息
             */
            override fun onError(e: Throwable) {
                SwmToastUtils.showToast("未连接到服务器,错误信息：" + e.message)
                mDisposable!!.dispose()
//                swmRequestComListener!!.onError(e)
            }

            /**
             * 整个请求完成调用，成功or失败都会走的路~
             */
            override fun onComplete() {
                mDisposable!!.dispose()
                if (swmRequestOnCompleteListener != null) swmRequestOnCompleteListener!!.onComplete()
            }
        }

    }
    //    private static class singleInstance {
    //        public static final SwmRxHttpUtils instance = new SwmRxHttpUtils();
    //    }
    //
    //    public static SwmRxHttpUtils getInstance() {
    //        return singleInstance.instance;
    //    }

    /**
     * 初始化 任务列表
     * @param userId
     * *
     * @return
     */
    fun getTaskList(userId: String): SwmRxHttpUtils {
        AppContext.apiService!!.getTaskList(userId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mBaseDataObserver)
        return this
    }

    /**
     *  下载文件
     */
    fun downloadFile(url: String): SwmRxHttpUtils {
        AppContext.apiService!!.downloadFile(url).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                val byteStream = response!!.body()!!.byteStream()
                val totalLength = response.body()!!.contentLength()
                SwmFileUtil().write2SDFromInput("", SwmSystemUtils.appVersion + ".apk", byteStream, totalLength)

            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
            }

        })
        return this
    }

    //    设置 请求完成 的 接口。
    private var swmRequestComListener: SwmRequestComListener? = null
    private var swmRequestOnNextListener: SwmRequestOnNextListener? = null
    private var swmRequestOnNextErrorListener: SwmRequestOnNextErrorListener? = null
    private var swmRequestOnCompleteListener: SwmRequestOnCompleteListener? = null
    private var swmRequestOnSubscribeListener: SwmRequestOnSubscribeListener? = null


    interface SwmRequestComListener {
        fun onNext(baseData: BaseData)
        fun onNextError(baseData: BaseData)
    }

    interface SwmRequestOnNextListener {
        fun onNext(baseData: BaseData)
    }

    interface SwmRequestOnNextErrorListener {
        fun onNextError(baseData: BaseData)
    }

    interface SwmRequestOnCompleteListener {
        fun onComplete()
    }

    interface SwmRequestOnSubscribeListener {
        fun onSubscribe(d: Disposable)
    }


}
