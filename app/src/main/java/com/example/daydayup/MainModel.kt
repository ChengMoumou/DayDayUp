package com.example.daydayup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.daydayup.utils.LogUtils

class MainModel: ViewModel() {
    val areaLiveData = MutableLiveData<String>()
    var area:String = ""

    val weatherAreaLiveData = areaLiveData.switchMap {
        LogUtils.i("调用")
        Repository.searchArea(area)
    }

    fun setAreaWeather(area:String){
        areaLiveData.value = area
    }
}