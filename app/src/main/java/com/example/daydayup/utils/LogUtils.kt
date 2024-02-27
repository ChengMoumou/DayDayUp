package com.example.daydayup.utils

import android.util.Log

class LogUtils {
    companion object{
        fun i(content: String){
            Log.i(javaClass.simpleName,content)
        }

        fun d(content: String){
            Log.d(javaClass.simpleName,content)
        }

        fun e(content: String){
            Log.e(javaClass.simpleName,content)
        }
    }
}