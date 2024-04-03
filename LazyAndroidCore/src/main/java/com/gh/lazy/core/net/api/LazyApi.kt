package com.gh.lazy.core.net.api

import com.gh.lazy.core.net.interceptor.LazyLogInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 默认的基础http请求 (仅http实现 有默认请求日志打印)
 * 因为在实际业务中, 接口仅支持https,或者需要自定义的ConverterFactory，日志打印等等
 * 可继承LazyBaseApi实现自己的需求
 */
class LazyApi : LazyBaseApi() {

    override fun initHttpClientBuilder(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        builder.apply {
            addInterceptor(LazyLogInterceptor())
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
        }

        return builder
    }

    override fun setRetrofitBuilder(builder: Retrofit.Builder): Retrofit.Builder {
        return builder.apply {
            addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        }
    }

    override fun getBaseUrl(): String {
        return "fdf"
    }

    fun <T> getApiService(apiService: Class<T>): T{
        return getApi(apiService)
    }

}