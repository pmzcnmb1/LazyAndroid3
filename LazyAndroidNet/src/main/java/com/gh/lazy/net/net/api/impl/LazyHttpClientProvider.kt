package com.gh.lazy.net.net.api.impl

import com.gh.lazy.net.net.api.ILazyHttpClientConfig
import com.gh.lazy.net.net.api.ILazyHttpClientProvider
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 默认配置的HttpClient
 * @author GaoHua
 */
class LazyHttpClientProvider : ILazyHttpClientProvider {
    override fun provideHttpClient(httpClientConfig: ILazyHttpClientConfig): OkHttpClient {
        return OkHttpClient.Builder().apply {
            eventListenerFactory(httpClientConfig.getEventListenerFactor())
            connectTimeout(httpClientConfig.getConnectTimeout(),TimeUnit.SECONDS)
            readTimeout(httpClientConfig.getReadTimeout(),TimeUnit.SECONDS)
            writeTimeout(httpClientConfig.getWriteTimeout(),TimeUnit.SECONDS)
            retryOnConnectionFailure(httpClientConfig.isNeedRetryOnConnectionFailure())
            httpClientConfig.getInterceptors().onEach { interceptor ->
                addInterceptor(interceptor)
            }
        }.build()
    }
    override fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    override fun provideCallAdapterFactory(): CallAdapter.Factory? {
        return null
    }

}