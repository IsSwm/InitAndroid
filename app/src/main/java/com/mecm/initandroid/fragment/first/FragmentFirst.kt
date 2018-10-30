package com.mecm.initandroid.fragment.first

import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.mecm.initandroid.R
import com.mecm.initandroid.activity.first.HeaderDemoActivity
import com.mecm.initandroid.entity.BaseData
import com.mecm.initandroid.fragment.FragmentCommon
import com.mecm.initandroid.fragment.first.adapter.HomeAdapter
import com.mecm.initandroid.fragment.first.adapter.HomeItem
import com.mecm.initandroid.views.BasePopupWindow
import kotlinx.android.synthetic.main.fragment_first.view.*
import kotlinx.android.synthetic.main.global_header.view.*


class FragmentFirst : FragmentCommon() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var drawerbar: ActionBarDrawerToggle
    private val POSITION_HEADER = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        init(view)
        return view
    }

    private fun init(view: View) {
        val homeData: ArrayList<HomeItem> = ArrayList()
        homeData.add(HomeItem("顶部内容演示"))
        view.home_rv.layoutManager = LinearLayoutManager(context)
        val homeAdapter = HomeAdapter(homeData)
        view.home_rv.adapter = homeAdapter
        homeAdapter.setOnItemClickListener { _, _, position ->
            when (position) {
                //  头部Demo演示
                POSITION_HEADER -> mStartActivity(context!!, HeaderDemoActivity::class.java)
            }
        }


        view.header_title.text = "弹出框演示"
        val popup = BasePopupWindow(activity!!)
        // 为popupWindow设置RecyclerView适配器
        popup.setView(PopupAdapter(getData()!!))
        // 设置宽度为全屏
        popup.setWidthAndHeight(1.0, 0.0)
        // 设置外部触摸是否可以关闭 默认 true
        popup.isOutsideTouchable = true
        // 设置可以通过监听返回键关闭PopupWindow
        popup.addKeyListener(true)
        // 设置窗口的透明度
        popup.mShowAlpha = 0.88f
//        view.button.setOnClickListener {
//            SwmToastUtils.showSingletonToast("弹出popup")
//            // 弹出popup
//            popup.showAtLocation(view.button, Gravity.BOTTOM, 0, 0)
//        }

    }

    //  关于更多的Adapter知识 参考于 大神的https://github.com/CymChad/BaseRecyclerViewAdapterHelper
    class PopupAdapter(dataList: List<PopupItem>) : BaseQuickAdapter<PopupItem, BaseViewHolder>(R.layout.item_popup_list, dataList) {
        override fun convert(helper: BaseViewHolder, item: PopupItem) {
            helper.setText(R.id.textview, item.title)
        }
    }

    class PopupItem constructor(var title: String) : BaseData() {
    }

    private var dataList: ArrayList<PopupItem>? = null
    // 生成Adapter 数据
    private fun getData(): ArrayList<PopupItem>? {
        return if (dataList == null) {
            // 参数 = 搜索框输入的内容
            dataList = ArrayList()
            for (i in 0..9) {
                dataList!!.add(PopupItem(i.toString()))
            }
            dataList
        } else {
            dataList
        }
    }

    companion object {
        fun newInstance(): FragmentFirst {
            val fragmentCommon = FragmentFirst()
            val bundle = Bundle()
            fragmentCommon.arguments = bundle
            return fragmentCommon
        }
    }
}