package com.mecm.initandroid

import android.content.Context
import android.os.Bundle
import com.mecm.initandroid.entity.BaseData
import com.mecm.initandroid.fragment.first.FragmentFirst
import com.mecm.initandroid.utils.SwmRxHttpUtils
import com.mecm.initandroid.utils.SwmRxHttpUtils.SwmRequestComListener
import com.mecm.moneybag.activity.BaseActivity
import com.ycl.tabview.library.TabViewChild
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    val context: Context = this


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val swmRxHttpUtils = SwmRxHttpUtils(object : SwmRequestComListener {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(baseData: BaseData) {
            }

            override fun onError(e: Throwable) {
            }

            override fun onComplete() {
            }
        })
        initArgs()
    }

    private fun initArgs() {
        val tabViewChildList = ArrayList<TabViewChild>()
        val tabViewChild01 = TabViewChild(R.mipmap.tab_home_sel, R.mipmap.tab_home_unsel, "PopupWindow", FragmentFirst.newInstance())
//        val tabViewChild02 = TabViewChild(R.mipmap.tab_home_sel, R.mipmap.tab_home_unsel,"" , FragmentWallet.newInstance())
//        val tabViewChild03 = TabViewChild(R.mipmap.tab_home_sel, R.mipmap.tab_home_unsel, , FragmentRecord.newInstance())
//        val tabViewChild04 = TabViewChild(R.mipmap.tab_home_sel, R.mipmap.tab_home_unsel, , FragmentUser.newInstance())


        tabViewChildList.add(tabViewChild01)
//        tabViewChildList.add(tabViewChild02)
//        tabViewChildList.add(tabViewChild03)
//        tabViewChildList.add(tabViewChild04)
        tabView.setTabViewChild(tabViewChildList, supportFragmentManager)
    }


}
