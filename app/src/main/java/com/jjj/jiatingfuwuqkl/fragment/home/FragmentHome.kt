package com.jjj.jiatingfuwuqkl.fragment.home

import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jjj.jiatingfuwuqkl.R
import com.jjj.jiatingfuwuqkl.entity.BaseData
import com.jjj.jiatingfuwuqkl.fragment.FragmentCommon
import com.jjj.jiatingfuwuqkl.utils.SwmToastUtils
import com.jjj.jiatingfuwuqkl.views.BasePopupWindow
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.global_header.view.*


class FragmentHome : FragmentCommon() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var drawerbar: ActionBarDrawerToggle

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        init(view)
        return view
    }

    private fun init(view: View) {
        view.header_title.text = "弹出框演示"
        val popup = BasePopupWindow(activity!!)
        // 为popupWindow设置RecyclerView适配器
        popup.setView(HomeAdapter(getData()!!))
        // 设置宽度为全屏
        popup.setWidthAndHeight(1.0, 0.0)
        // 设置外部触摸是否可以关闭 默认 true
        popup.isOutsideTouchable = true
        // 设置可以通过监听返回键关闭PopupWindow
        popup.addKeyListener(true)
        // 设置窗口的透明度
        popup.mShowAlpha = 0.88f
        view.button.setOnClickListener {
            SwmToastUtils.showSingletonToast("弹出popup")
            // 弹出popup
            popup.showAtLocation(view.button, Gravity.BOTTOM, 0, 0)
        }

    }

    //  关于更多的Adapter知识 参考于 大神的https://github.com/CymChad/BaseRecyclerViewAdapterHelper
    class HomeAdapter(dataList: List<HomeItem>) : BaseQuickAdapter<HomeItem, BaseViewHolder>(R.layout.item_popup_list, dataList) {
        override fun convert(helper: BaseViewHolder, item: HomeItem) {
            helper.setText(R.id.textview_list, "PopupWindowText")
        }
    }

    class HomeItem constructor(var title: String) : BaseData() {
    }

    private var dataList: ArrayList<HomeItem>? = null
    // 生成Adapter 数据
    private fun getData(): ArrayList<HomeItem>? {
        return if (dataList == null) {
            // 参数 = 搜索框输入的内容
            dataList = ArrayList()
            for (i in 0..9) {
                dataList!!.add(HomeItem(i.toString()))
            }
            dataList
        } else {
            dataList
        }
    }

    companion object {
        fun newInstance(): FragmentHome {
            val fragmentCommon = FragmentHome()
            val bundle = Bundle()
            fragmentCommon.arguments = bundle
            return fragmentCommon
        }
    }
}