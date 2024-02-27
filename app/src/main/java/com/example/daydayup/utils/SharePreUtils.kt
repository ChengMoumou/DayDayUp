package com.example.daydayup.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.MyApplication

class SharePreUtils private constructor() {
    init {
        sharePreUtils = MyApplication.context.getSharedPreferences("share",Context.MODE_PRIVATE)
        mEditor = sharePreUtils.edit()
    }

    fun putSpString(key:String, value:String){
        mEditor.putString(key, value)
        mEditor.commit()
    }

    fun getSpString(key: String): String?{
        return sharePreUtils.getString(key,"")
    }

    fun removeKey(key: String){
        mEditor.remove(key)
        mEditor.commit()
    }

    companion object{
        lateinit var sharePreUtils: SharedPreferences
        lateinit var mEditor: SharedPreferences.Editor
        var mySharePreUtils:SharePreUtils? = null
        // 单例模式
        fun getInstance():SharePreUtils{
            if (mySharePreUtils == null){
                mySharePreUtils = SharePreUtils()
            }
            return mySharePreUtils!!
        }
    }
}