package com.gh.lazy.net.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gh.lazy.net.net.exception.LazyException
import com.gh.lazy.net.net.exception.LazyHttpExceptionHandle
import com.gh.lazy.net.net.log.logE
import com.gh.lazy.net.net.resp.LazyResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import kotlin.coroutines.CoroutineContext

/**
 *@author gh
 *
 *
 *
 * Views shouldn't directly trigger any coroutines to perform business logic.
 * Instead, defer that responsibility to the ViewModel.
 * This makes your business logic easier to test as ViewModel objects can be unit tested,
 * instead of using instrumentation tests that are required to test views
 *
 *视图不应直接触发任何协程来执行业务逻辑，而应将这项工作委托给 ViewModel
 *
 */

fun processException(exception: Throwable) {
    printExceptionInfo(LazyHttpExceptionHandle.processException(exception))
}

private fun printExceptionInfo(e: LazyException) {
    "StackTrace:".plus(e.stackTraceToString()).logE()
    "Exception is:".plus(e.message).logE()
}

/**
 *  网络请求 自定义数据实体
 *  @param onResponse 不对返回数据做统一处理,自行处理
 *  @param onResponse  默认主线程
 *  @param responseContext
 *  @param requestContext
 */
fun <T> ViewModel.requestAny(
    onRequest: suspend () -> T,
    onResponse: (T?) -> Unit,
    requestContext: CoroutineContext = Dispatchers.IO,
    responseContext: CoroutineContext = Dispatchers.Main
): Job {
    return viewModelScope.launch {
        runCatching {
            withContext(requestContext) {
                onRequest.invoke()
            }
        }.onSuccess { response ->
            withContext(responseContext) {
                onResponse.invoke(response)
            }
        }.onFailure { exception ->
            processException(exception)
        }
    }
}


