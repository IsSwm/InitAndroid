package com.jjj.jiatingfuwuqkl

import android.os.Bundle
import com.jjj.jiatingfuwuqkl.entity.BaseData
import com.jjj.jiatingfuwuqkl.fragment.home.FragmentHome
import com.jjj.jiatingfuwuqkl.utils.SwmRxHttpUtils
import com.jjj.jiatingfuwuqkl.utils.SwmRxHttpUtils.SwmIsRequestComListener
import com.jjj.moneybag.activity.BaseActivity
import com.ycl.tabview.library.TabViewChild
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val swmRxHttpUtils = SwmRxHttpUtils(object : SwmIsRequestComListener {
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
        val tabViewChild01 = TabViewChild(R.mipmap.tab_home_sel, R.mipmap.tab_home_unsel, "PopupWindow", FragmentHome.newInstance())
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
