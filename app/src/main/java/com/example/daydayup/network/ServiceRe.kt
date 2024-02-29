package com.example.daydayup.network

import com.example.daydayup.model.WeatherReq
import com.example.daydayup.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ServiceRe {
    @FormUrlEncoded
    @POST("9-2?showapi_appid=1548377&showapi_sign=a220b08ab42c477e993f044a36e7ee81")
//     fun weatherService(@Body weatherReq: WeatherReq):Call<WeatherResponse>
    fun weatherService(@Field("area") first: String):Call<WeatherResponse>
}