package com.example.daydayup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.daydayup.utils.LogUtils

class MainActivity : AppCompatActivity() {
    val viewModel by lazy{
        ViewModelProvider(this).get(MainModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.area = "深圳"
        viewModel.setAreaWeather(viewModel.area)
        viewModel.weatherAreaLiveData.observe(this, Observer {
            res -> LogUtils.i("收到的result是====${res.toString()}")
        })
    }
}