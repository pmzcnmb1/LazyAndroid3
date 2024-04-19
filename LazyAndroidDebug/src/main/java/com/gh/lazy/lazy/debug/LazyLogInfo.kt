package com.gh.lazy.lazy.debug

import com.gh.lazy.lazy.debug.utils.HttpLogHelper
import com.gh.lazy.net.net.interceptor.ILazyLogInfo
import okhttp3.Request

class LazyLogInfo :ILazyLogInfo{
    override fun getLogInfo(
        request: Request,
        requestBodyStr: String,
        requestTime: Long,
        duration: Long,
        responseBodyStr: String
    ) {
        HttpLogHelper.getInstance().parseDataAndAdd(request,requestBodyStr,requestTime,duration,responseBodyStr)
    }
}