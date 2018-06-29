package com.jjj.initandroid.fragment.first.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jjj.initandroid.R

//  关于更多的Adapter知识 参考于 大神的https://github.com/CymChad/BaseRecyclerViewAdapterHelper
    class HomeAdapter(dataList: ArrayList<HomeItem>) : BaseQuickAdapter<HomeItem, BaseViewHolder>(R.layout.item_popup_list, dataList) {
        override fun convert(helper: BaseViewHolder, item: HomeItem) {
            helper.setText(R.id.textview, item.title)
        }
    }