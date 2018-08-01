package com.mecm.initandroid.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mecm.initandroid.R

open class FragmentCommon : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_common, container, false)
        view.textView.text = arguments!!.getString("text")
        return view
    }

    // 启动activity 使用 mStartActivity(context,*::class.java)
    fun mStartActivity(packageContext: Context, cls:Class<*> ){
        packageContext.startActivity(Intent(packageContext, cls))
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
