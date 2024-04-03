package com.gh.lazy.core.net.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

/**
 * @author GaoHua
 * 网络请求构建器基类
 * 具体配置
 */
abstract class LazyBaseApi {

    var retrofit: () -> Retrofit = {
        Retrofit.Builder().apply {
            baseUrl(getBaseUrl()).client(okHttpClient).addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().create()
                )
            )
        }.build()
    }
    private val okHttpClient: OkHttpClient
        get() {
            return initHttpClientBuilder(OkHttpClient.Builder()).build()
        }

    protected fun <T> getApi(serviceClass: Class<T>): T {
        tt().onBuildRetrofit(Retrofit.Builder(),OkHttpClient.Builder().build()).create(serviceClass)
        return retrofit.invoke().create(serviceClass)
    }

    abstract fun initHttpClientBuilder(builder: OkHttpClient.Builder): OkHttpClient.Builder

    abstract fun setRetrofitBuilder(builder: Retrofit.Builder): Retrofit.Builder

    abstract fun getBaseUrl(): String

}



