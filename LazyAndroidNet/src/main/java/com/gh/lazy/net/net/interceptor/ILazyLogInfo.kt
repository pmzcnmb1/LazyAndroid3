package com.gh.lazy.net.net.interceptor

import okhttp3.Request

interface ILazyLogInfo {
    fun getLogInfo(request:Request,requestBodyStr:String,requestTime:Long,duration:Long,responseBodyStr:String)
}