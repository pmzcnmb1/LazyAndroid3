package com.gh.lazy.net.net.core

import com.gh.lazy.net.net.api.ILazyHttpClientConfig
import com.gh.lazy.net.net.api.ILazyHttpClientProvider
import com.gh.lazy.net.net.api.LazyAndroidApi

/**
 * 网络框架初始化
 * @author GaoHua
 * @see "关于动态切换baseUrl的问题------->
 * https://square.github.io/retrofit/2.x/retrofit/retrofit2/http/Url.html"
 */
class LazyNet private constructor() {

    private lateinit var api: LazyAndroidApi

    companion object {
        private val instance: LazyNet by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            LazyNet()
        }

        fun getInstance(): LazyNet {
            return instance
        }
    }

    fun getApi(): LazyAndroidApi {
        return if (this::api.isInitialized) api else throw NullPointerException("LazyNet Is Not Initialize!")
    }

    /**
     * 基础的http请求,使用框架提供的默认设置
     *  @param baseUrl 服务器地址
     */
    fun initialize(baseUrl: String) {
        this.api = LazyAndroidApi(baseUrl = baseUrl)
    }

    /**
     *  根据自己的业务传入配置类
     *  比如自定义拦截器,https校验等
     *  @param baseUrl 服务器地址
     *  @param httpClientProvider HttpClient提供者,如果不传则使用框架默认的httpClientProvider
     *  @param httpClientConfig httpClient配置项,不传则使用框架默认的httpClientConfig
     */
    fun initialize(
        baseUrl: String,
        httpClientProvider: ILazyHttpClientProvider,
        httpClientConfig: ILazyHttpClientConfig
    ) {
        this.api = LazyAndroidApi(
            baseUrl = baseUrl,
            httpClientProvider = httpClientProvider,
            httpClientConfig = httpClientConfig
        )
    }
}