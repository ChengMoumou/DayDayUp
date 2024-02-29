package com.example.daydayup

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.daydayup.utils.LogUtils
import com.example.daydayup.utils.NotificationReceiver

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
        notice()
    }

    /**
     * 要打卡的通知，有空做一个，输入提醒内容到时间提醒
     */
    fun notice(){
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val pendingIntent = PendingIntent.getBroadcast(this, 0X102, Intent(this, NotificationReceiver::class.java), 0)
        //设置闹钟
        alarmManager.set(AlarmManager.RTC_WAKEUP, 5000, pendingIntent)
    }
}