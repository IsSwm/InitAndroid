package com.jjj.jiatingfuwuqkl.utils

import com.jjj.jiatingfuwuqkl.AppContext
import com.jjj.jiatingfuwuqkl.entity.BaseData

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * 网络请求 基于 retrofit 与 rxjava
 */
class SwmRxHttpUtils
/**
 * 请求中返回的各种接口
 * @param swmIsRequestComListener
 */
(swmIsRequestComListener: SwmIsRequestComListener) {

    private val mBaseDataObserver: Observer<BaseData>

    init {
        mBaseDataObserver = object : Observer<BaseData> {

            private var mDisposable: Disposable? = null
            /**
             * 可以在此做一些初始化操作，应该是这样的。
             */
            override fun onSubscribe(d: Disposable) {
                mDisposable = d
                swmIsRequestComListener.onSubscribe(d)
            }

            /**
             * 请求返回的数据 封装的基类
             *  status 判断可根据你亲爱的服务器大神的返回~
             */
            override fun onNext(baseData: BaseData) {
                //    如果 服务器 返回的  请求 状态码 等于success 代码有数据
                if ("success" == baseData.status) {
                    swmIsRequestComListener.onNext(baseData)
                } else {
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
                swmIsRequestComListener.onError(e)
            }

            /**
             * 整个请求完成调用，成功or失败都会走的路~
             */
            override fun onComplete() {
                mDisposable!!.dispose()
                swmIsRequestComListener.onComplete()
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

    //    设置 请求完成 的 接口。
    private var mSwmIsRequestComListener: SwmIsRequestComListener? = null

    fun setSwmIsRequestComListener(swmIsRequestComListener: SwmIsRequestComListener) {
        mSwmIsRequestComListener = swmIsRequestComListener
    }

    interface SwmIsRequestComListener {
        fun onSubscribe(d: Disposable)

        fun onNext(baseData: BaseData)

        fun onError(e: Throwable)

        fun onComplete()
    }


}
