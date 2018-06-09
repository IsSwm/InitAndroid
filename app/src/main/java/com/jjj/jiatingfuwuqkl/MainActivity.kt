package com.jjj.jiatingfuwuqkl

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jjj.jiatingfuwuqkl.entity.BaseData
import com.jjj.jiatingfuwuqkl.utils.SwmRxHttpUtils
import com.jjj.jiatingfuwuqkl.utils.SwmRxHttpUtils.SwmIsRequestComListener
import com.jjj.jiatingfuwuqkl.utils.SwmToastUtils
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val swmRxHttpUtils = SwmRxHttpUtils(object : SwmIsRequestComListener {
            override fun onSubscribe(d: Disposable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onNext(baseData: BaseData) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError(e: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onComplete() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
        textview.setOnClickListener {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            SwmToastUtils.showToast("123")
        }


    }

}
