package com.mecm.initandroid.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jjj.jiatingfuwuqkl.utils.SwmUser
import com.mecm.initandroid.R
import kotlinx.android.synthetic.main.fragment_common.view.*
import org.json.JSONObject

open class FragmentCommon : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_common, container, false)
        view.textView.text = arguments!!.getString("text")
        return view
    }

    // 启动activity 使用 mStartActivity(context,*::class.java)
    fun mStartActivity(cls: Class<*>) {
        context!!.startActivity(Intent(context, cls))
    }

    // 启动activity 使用 mStartActivity(context,*::class.java)
    fun mStartActivity(cls: Class<*>, url: String, param: String) {
        val intent = Intent(context, cls)
        intent.putExtra("url", url)
        intent.putExtra("params", param)
        context!!.startActivity(intent)
    }

    // 启动activity 使用 mStartActivity(context,*::class.java)
    fun mStartActivity(cls: Class<*>, url: String, param: JSONObject) {
        param.put("token", SwmUser.getUser(context!!))
        val intent = Intent(context, cls)
        intent.putExtra("url", url)
        intent.putExtra("params", param.toString())
        this.startActivity(intent)
    }


    companion object {
        fun newInstance(text: String): FragmentCommon {
            val fragmentCommon = FragmentCommon()
            val bundle = Bundle()
            bundle.putString("text", text)
            fragmentCommon.arguments = bundle
            return fragmentCommon
        }
    }
}
