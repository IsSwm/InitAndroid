package com.jjj.jiatingfuwuqkl.utils

import com.jjj.jiatingfuwuqkl.AppContext
import com.jjj.jiatingfuwuqkl.entity.BaseData

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by 九贡 on 2017/10/8.
 */

class SwmRxHttpUtils
/**
 * 请求完成做的事情
 * @param swmIsRequestComListener
 */
(swmIsRequestComListener: SwmIsRequestComListener) {

    private val mBaseDataObserver: Observer<BaseData>

    init {
        mBaseDataObserver = object : Observer<BaseData> {

            private var mDisposable: Disposable? = null

            override fun onSubscribe(d: Disposable) {
                mDisposable = d
                swmIsRequestComListener.onSubscribe(d)
            }

            override fun onNext(baseData: BaseData) {
                //                如果 服务器 返回的  请求 状态码 等于success 代码有数据
                if ("success" == baseData.status) {
                    swmIsRequestComListener.onNext(baseData)
                } else {
                    //                    显示服务器的错误信息
                    SwmToastUtils.showToast(baseData.message)
                }
            }

            override fun onError(e: Throwable) {
                SwmToastUtils.showToast("未连接到服务器,错误信息：" + e.message)
                mDisposable!!.dispose()
                swmIsRequestComListener.onError(e)
            }

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
