package com.gh.lazy.net.net.api

import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter

/**
 * @author GaoHua
 * 通用的网络请求配置
 */
interface ILazyHttpClientProvider {
    fun provideHttpClient(httpClientConfig:ILazyHttpClientConfig):OkHttpClient
    fun provideConverterFactory(): Converter.Factory
    fun provideCallAdapterFactory():CallAdapter.Factory?
}