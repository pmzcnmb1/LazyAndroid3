package com.gh.lazy.core.net.api

import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit

/**
 * @author GaoHua
 * 网络请求构建器基类
 * 继承LazyBaseApi实现自己的子类即可 自己定义配置等
 */
abstract class LazyBaseApi {

    private val retrofit by lazy {
        Retrofit.Builder().apply {
            baseUrl(getBaseUrl())
            client(okHttpClient)
            addConverterFactory(setConverterFactory())
            setCallAdapterFactory()?.let { addCallAdapterFactory(it) }
        }.build()
    }

    private val okHttpClient: OkHttpClient
        get() {
            return initHttpClientBuilder(OkHttpClient.Builder()).build()
        }

    protected fun <T> getApiService(serviceClass: Class<T>): T? {
        return retrofit?.create(serviceClass)
    }

    protected fun getHttpClient(): OkHttpClient {
        return okHttpClient
    }

    abstract fun setConverterFactory(): Converter.Factory
    abstract fun setCallAdapterFactory(): CallAdapter.Factory?
    abstract fun initHttpClientBuilder(builder: OkHttpClient.Builder): OkHttpClient.Builder

    abstract fun getBaseUrl(): String

}



