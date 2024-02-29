package com.example

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper

class MyApplication : Application() {
    companion object{
        lateinit var context: Context
    }
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
/*        Handler(mainLooper).post {
            Runnable {
                kotlin.run {
                    try {
                        Looper.loop()
                    }catch (e:Exception){

                    }
                }
            }
        }*/
    }
}