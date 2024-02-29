package com.example.daydayup.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object Network {
    val service = ServiceCreator.createRetrofit(ServiceRe::class.java)
    suspend fun weather(weatherReq: String) = service.weatherService(weatherReq).await()

    /**
     * 对接口返回统一处理
     */
    suspend fun <T> Call<T>.await() : T {
        return suspendCoroutine<T> { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) {
                        continuation.resume(body)
                    } else {
                        continuation.resumeWithException(RuntimeException("response is null"))
                    }
                }
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(RuntimeException(t))
                }
            })
        }
    }
}