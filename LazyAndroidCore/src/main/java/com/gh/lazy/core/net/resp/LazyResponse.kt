package com.gh.lazy.core.net.resp

/**
 * @author gh
 *  服务器返回数据的基类
 */
abstract class LazyResponse<T> {

    abstract fun isSuccess(): Boolean

    abstract fun getResponseData(): T?

    abstract fun getResponseCode(): Int

    abstract fun getResponseMsg(): String

}