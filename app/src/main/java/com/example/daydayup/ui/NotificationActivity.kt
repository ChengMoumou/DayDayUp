package com.example.daydayup.ui

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.example.daydayup.MainActivity
import com.example.daydayup.R
import com.example.daydayup.utils.NotificationReceiver

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        notice()
    }

    /**
     * 要打卡的通知，有空做一个，输入提醒内容到时间提醒
     */
    fun notice(){
        // 获取 NotificationManager
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 使用 NotificationChannel 构建通知渠道
            val channel = NotificationChannel("yes", "normal", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }

        val intent = Intent(this, MainActivity::class.java)
        // requestCode 一般用不到
        // flags 用于确定 PendingIntent 的行为
        val pi = PendingIntent.getActivity(this, 0, intent, 1)

        val notification = NotificationCompat.Builder(this, "yes")
            .setContentTitle("打卡") // 标题内容
            .setContentText("study") // 正文内容
            .setSmallIcon(R.mipmap.launcher)
            .setContentIntent(pi)
            .build()

        // 要保证每个通知指定的 id 都是不同的
        manager.notify(1,notification)
    }
}