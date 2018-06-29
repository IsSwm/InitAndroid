package com.jjj.jiatingfuwuqkl.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jjj.jiatingfuwuqkl.R
import kotlinx.android.synthetic.main.fragment_common.*
import kotlinx.android.synthetic.main.fragment_common.view.*

open class FragmentCommon : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_common, container, false)
        view.textView.text = arguments!!.getString("text")
        return view
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