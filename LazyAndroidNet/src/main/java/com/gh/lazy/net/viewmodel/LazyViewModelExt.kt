package com.gh.lazy.net.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gh.lazy.net.net.exception.LazyHttpExceptionHandle
import com.gh.lazy.net.net.log.LogUtils
import com.gh.lazy.net.net.log.logE
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import kotlin.coroutines.CoroutineContext

/**
 *@author gh
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
    try {
        val e = LazyHttpExceptionHandle.processException(exception)
        "StackTrace:".plus(e.stackTraceToString()).logE()
        "Exception is:".plus(e.message).logE()
    } catch (e: Exception) {
        "StackTrace:".plus(e.stackTraceToString()).logE()
        "Exception is:".plus(e.message).logE()
    }

}

/**
 *  网络请求 返回数据可能是任何格式  如调用第三方api等
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

