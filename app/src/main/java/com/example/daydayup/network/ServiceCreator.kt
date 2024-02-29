package com.example.daydayup.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
* 使用 object 关键字创建的类是一个简单的单例对象。这意味着在应用程序中只能有一个该对象的实例。
* */
object ServiceCreator {
    const val APP_ID = "1548377"
    const val SIGN = "a220b08ab42c477e993f044a36e7ee81"
    private const val BASE_URL = "https://route.showapi.com/"

    fun retrofitClient(): OkHttpClient = OkHttpClient().newBuilder().apply {
        this.addInterceptor(HeadersInterceptor())
    }.build()
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(retrofitClient())
        .build()

    // 通过泛型的设计，函数可以适用于不同类型的服务接口。
    fun<T> createRetrofit(serviceClass: Class<T>) : T = retrofit.create(serviceClass)
    // 函数成为内联函数
    inline fun<reified T> create(): T = createRetrofit(T::class.java)
}