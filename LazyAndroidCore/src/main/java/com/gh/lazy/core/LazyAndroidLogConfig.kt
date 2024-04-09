package com.gh.lazy.core

import com.elvishew.xlog.LogLevel
import com.gh.lazy.core.net.interceptor.LazyLogInterceptor

/**
 * 日志输出配置 默认全部输出
 *
 */
data class LazyAndroidLogConfig(
    val httpLogShowLevel: LazyLogInterceptor.Level = LazyLogInterceptor.Level.ALL,
    val lifecycleLogShowLevel: Int = LogLevel.ALL
)