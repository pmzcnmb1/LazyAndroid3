package com.gh.lazy.debug.model

import java.io.Serializable

data class ErrorLogModel(
    var title: String? = null,
    var logContent: String? = null,
    var time: String? = null
):Serializable
