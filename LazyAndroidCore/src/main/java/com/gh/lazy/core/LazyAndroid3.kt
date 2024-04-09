package com.gh.lazy.core

import android.content.Context
import com.elvishew.xlog.LogLevel
import com.elvishew.xlog.XLog
import com.gh.lazy.core.net.interceptor.LazyLogInterceptor
import com.tencent.mmkv.MMKV

/**
 * 框架入口
 * @author gh
 */
object LazyAndroid3{
    fun init(config: LazyAndroidConfig) {
        MMKV.initialize(config.context)
        XLog.init(config.logConfig.lifecycleLogShowLevel)
    }

    fun init(context: Context) {
        MMKV.initialize(context)
        XLog.init(LogLevel.ALL)
    }
}