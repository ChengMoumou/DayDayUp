package com.example.daydayup

import androidx.lifecycle.liveData
import com.example.daydayup.network.Network
import com.example.daydayup.utils.LogUtils
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

object Repository {

    // 未处理
    fun searchArea(area: String) = up(Dispatchers.IO){
        val response = Network.weather(area)
        LogUtils.i("收到的请求是===${response.toString()}")
        Result.success(response)
    }

    private fun <T> up(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context){
        val result = try {
            block()
        }catch (e: Exception){
            Result.failure<T>(e)
        }
        emit(result)
    }
}