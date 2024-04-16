package com.gh.lazy.net.net.api

import com.gh.lazy.net.net.api.impl.LazyHttpClientConfig
import com.gh.lazy.net.net.api.impl.LazyHttpClientProvider
import retrofit2.Retrofit

/**
 * @author GaoHua
 * 网络请求构建器
 * @param baseUrl 接口地址
 * @param httpClientProvider HttpClient提供者,如果不传则使用框架默认的httpClientProvider
 * @param httpClientConfig httpClient配置项,不传则使用框架默认的httpClientConfig
 */
class LazyAndroidApi(
   private val baseUrl: String,
   private val httpClientProvider: ILazyHttpClientProvider? = null,
   private val httpClientConfig: ILazyHttpClientConfig? = null
) {

    private val defaultHttpClientProvider by lazy {
        LazyHttpClientProvider()
    }

    private val defaultHttpClientConfig by lazy {
        LazyHttpClientConfig()
    }

    private val retrofit by lazy {
        Retrofit.Builder().apply {
            baseUrl(baseUrl)

            client(
                httpClientConfig?.let { config ->
                    httpClientProvider?.provideHttpClient(config)
                } ?: defaultHttpClientProvider.provideHttpClient(defaultHttpClientConfig)
            )

            addConverterFactory(
                httpClientProvider?.provideConverterFactory()
                    ?: defaultHttpClientProvider.provideConverterFactory()
            )

            (httpClientProvider?.provideCallAdapterFactory()
                ?: defaultHttpClientProvider.provideCallAdapterFactory())?.let { callAdapterFactory ->
                addCallAdapterFactory(callAdapterFactory)
            }
        }.build()
    }

    fun <T> getService(serviceClass: Class<T>): T? {
        return retrofit?.create(serviceClass)
    }
}



