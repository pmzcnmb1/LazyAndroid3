package com.gh.lazy.core.base

import java.util.concurrent.TimeUnit

/**
 * 框架网络请求配置类
 * 默认60秒
 * @author gh
 */
data class LazyHttpClientConfig(
    var connectTimeout: Long = 60,
    var readTimeout: Long = 60,
    var writeTimeout: Long = 60,
    var unit: TimeUnit = TimeUnit.SECONDS,
    var retryOnConnectionFailure: Boolean = true
)
