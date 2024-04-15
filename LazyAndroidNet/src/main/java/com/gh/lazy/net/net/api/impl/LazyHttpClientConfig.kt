package com.gh.lazy.net.net.api.impl

import com.gh.lazy.net.net.api.ILazyHttpClientConfig
import com.gh.lazy.net.net.interceptor.LazyLogInterceptor
import okhttp3.Interceptor

/**
 * HttpClient默认配置项,方便懒人
 * 也可以根据自己的需求实现自己的HttpClientConfig
 */
class LazyHttpClientConfig :ILazyHttpClientConfig{
    override fun getInterceptors(): List<Interceptor> {
        return listOf(LazyLogInterceptor())
    }

    override fun getConnectTimeout(): Long {
        return 30
    }

    override fun getReadTimeout(): Long {
        return 30
    }

    override fun getWriteTimeout(): Long {
        return 30
    }

    override fun getRetryCount(): Int {
        return 3
    }

    override fun getMaxIdleConnections(): Int {
        return 5
    }

    override fun getKeepAliveDuration(): Long {
        return 5
    }

    override fun isNeedRetryOnConnectionFailure(): Boolean {
        return true
    }

    override fun canProxy(): Boolean {
        return true
    }


}