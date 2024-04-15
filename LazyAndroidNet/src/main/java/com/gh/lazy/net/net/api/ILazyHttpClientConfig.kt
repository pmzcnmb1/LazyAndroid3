package com.gh.lazy.net.net.api

import okhttp3.EventListener
import okhttp3.Interceptor

/**
 * httpClient配置项
 */
interface ILazyHttpClientConfig {
    fun getInterceptors():List<Interceptor>
    fun getConnectTimeout(): Long
    fun getReadTimeout(): Long
    fun getWriteTimeout(): Long
    fun getRetryCount(): Int
    fun getMaxIdleConnections(): Int
    fun getKeepAliveDuration(): Long
    fun isNeedRetryOnConnectionFailure():Boolean
    fun canProxy():Boolean

    fun getEventListenerFactor(): EventListener.Factory{
        return LazyHttpEventListener.FACTORY
    }

}