package com.gh.lazy.net.net.api

import com.gh.lazy.net.net.interceptor.LazyLogInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 基础http请求,方便使用
 * 推荐继承LazyBaseApi实现自己的业务需求
 */
 class LazyApi : LazyBaseApi() {
    override fun setConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create(GsonBuilder().create())
    }

    override fun setCallAdapterFactory(): CallAdapter.Factory? {
        return null
    }

    override fun initHttpClientBuilder(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        builder.apply {
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
            addInterceptor(LazyLogInterceptor())
        }

        return builder
    }

    override fun getBaseUrl(): String {
       return ""
    }
}