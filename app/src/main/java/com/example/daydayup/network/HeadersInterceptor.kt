package com.example.daydayup.network

import com.example.daydayup.utils.LogUtils
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Response
import okio.Buffer
import java.nio.charset.StandardCharsets

class HeadersInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        LogUtils.i("发送请求体====${req.body().toString()}")
        LogUtils.i("发送请求链接====${req.url().toString()}")

        val response = chain.proceed(req)
        var source = response.body()?.source()
        source?.request(Long.MAX_VALUE);
        val buffer: Buffer = source!!.buffer()
        var charset = StandardCharsets.UTF_8
        val contentType: MediaType = response.body()?.contentType()!!

        if (contentType != null) {
            charset = contentType.charset(charset)
        }

        val string = buffer.clone().readString(charset) //未解密
        LogUtils.i("收到的请求====${response.body().toString()}===============$string")
        return response
    }
}