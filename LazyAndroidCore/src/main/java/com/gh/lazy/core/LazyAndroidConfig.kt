package com.gh.lazy.core

import android.content.Context

data class LazyAndroidConfig(
    val context: Context,
    val logConfig: LazyAndroidLogConfig = LazyAndroidLogConfig()
)