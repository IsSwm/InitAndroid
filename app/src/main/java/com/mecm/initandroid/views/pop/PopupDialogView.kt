package com.mecm.initandroid.views.pop

import android.content.Context
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.mecm.initandroid.R

class PopupDialogView {

    var popupDialog: BasePopupWindow? = null

    constructor(context: Context) {
        this.popupDialog = BasePopupWindow(context)
        popupDialog!!.setWidthAndHeight(1.0, 0.0)
    }

    fun getPopup(): BasePopupWindow {
        return popupDialog!!
    }

    fun setAdapter(title: String): PopupDialogAdapter {
        var list = ArrayList<String>()
        list.add(title)

        val adapter = PopupDialogAdapter(list)
        getPopup().setView(adapter)
        getPopup().setWidthAndHeight(1.0, 0.0)
        return adapter
    }


    //    适配器
    class PopupDialogAdapter(dataList: List<String>) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_popup_dialog, dataList) {
        interface PopupDialogListener {
            fun dismissPopup()
            fun confirm()
        }

        var popupDialogListener: PopupDialogListener? = null
        override fun convert(helper: BaseViewHolder, item: String) {
            if (item == "版本更新提醒") {
                helper.setText(R.id.item_popup_dialog_confirm,"更新")
            }
            helper.setText(R.id.item_popup_dialog_title, item)
            helper.getView<TextView>(R.id.item_popup_dialog_cancel).setOnClickListener {
                if (popupDialogListener != null) {
                    popupDialogListener!!.dismissPopup()
                }
            }
            helper.getView<TextView>(R.id.item_popup_dialog_confirm).setOnClickListener {
                if (popupDialogListener != null) {
                    popupDialogListener!!.confirm()
                }
            }

        }
    }
}