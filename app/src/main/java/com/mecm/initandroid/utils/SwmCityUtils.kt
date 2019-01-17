package com.mecm.initandroid.utils

import android.content.Context
import com.lljjcoder.Interface.OnCityItemClickListener
import com.lljjcoder.bean.CityBean
import com.lljjcoder.bean.DistrictBean
import com.lljjcoder.bean.ProvinceBean
import com.lljjcoder.citywheel.CityConfig
import com.lljjcoder.style.citypickerview.CityPickerView


object SwmCityUtils {

    data class CityDetail(
            var totalAddress: String,
            var mProvince: String,
            var mCity: String,
            var mArea: String,
            var long: String,
            var lat: String
    )

    var cityListener: CityListener? = null

    interface CityListener {
        fun cityComplete(mCity: CityDetail)
    }

    var mPicker: CityPickerView? = null

    fun getCityPicker(): CityPickerView? {
        if (mPicker == null) {
            initCityPicker()
        }
        return mPicker
    }

    fun showCityPicker(context: Context) {
        initCityPicker(context)
    }

    private fun initCityPicker(context: Context) {
        initCityPicker()
        mPicker!!.init(context)
        mPicker!!.showCityPicker()

    }

    private fun initCityPicker() {
        if (mPicker == null) {
            mPicker = CityPickerView()
        }
        //添加默认的配置，不需要自己定义
        val cityConfig = CityConfig.Builder().build()
        cityConfig.defaultProvinceName = "河南省"
        cityConfig.defaultCityName = "郑州市"
        cityConfig.defaultDistrict = "金水区"

        mPicker!!.setConfig(cityConfig)


        //监听选择点击事件及返回结果
        mPicker!!.setOnCityItemClickListener(object : OnCityItemClickListener() {
            override fun onSelected(province: ProvinceBean?, city: CityBean?, district: DistrictBean?) {
                var mCity = CityDetail("", "", "", "", "", "")
                //省份
                if (province != null) {
                    mCity.mProvince = province.name
                    mCity.totalAddress += mCity.mProvince
                }
                //城市
                if (city != null) {
                    mCity.mCity = city.name
                    mCity.totalAddress += mCity.mCity
                }
                //地区
                if (district != null) {
                    mCity.mArea = district.name
                    mCity.totalAddress += mCity.mArea
                }
                if (cityListener != null) {
                    cityListener!!.cityComplete(mCity)
                }

//                SwmRxHttpUtils(object : SwmRxHttpUtils.SwmRequestComListener {
//                    override fun onSubscribe(d: Disposable) {
//                    }
//
//                    override fun onNext(baseData: BaseData) {
//                        val addressToLongLatEt = baseData as AddressToLongLatEt
//                        val location = addressToLongLatEt.geocodes[0].location
//                        val split = location.split(",")
//                        SwmLogUtils.e(split.toString())
//                        mCity.long = split[0]
//                        mCity.lat = split[1]
//                    }
//
//                    override fun onComplete() {
//                    }
//
//                    override fun onNextError() {
//                    }
//
//                }).addressToLongLat(mCity.mArea)
            }

            override fun onCancel() {
                SwmToastUtils.showSingleLongToast("已取消")
            }
        })
    }
}