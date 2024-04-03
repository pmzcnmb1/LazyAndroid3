package com.gh.lazy.core.net.exception

import android.net.ParseException
import com.google.gson.JsonParseException
import com.google.gson.stream.MalformedJsonException
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import com.google.gson.JsonSyntaxException

/**
 * @Description: 根据异常返回相关的错误信息
 * @Author: GaoHua
 * @CreateDate: 2021/4/7 16:58
 * @Version: 1.0
 */
object LazyExceptionHandle {

    fun handleException(e: Throwable?): LazyException {
        val ex: LazyException
        e?.let {
            when (it) {
                is HttpException -> {
                    ex = LazyException(Error.NETWORK_ERROR, e)
                    return ex
                }

                is JsonSyntaxException ->{
                    ex = LazyException(Error.JSON_ERROR, e)
                    return ex
                }

                is JsonParseException, is JSONException, is ParseException, is MalformedJsonException -> {
                    ex = LazyException(Error.PARSE_ERROR, e)
                    return ex
                }
                is ConnectException -> {
                    ex = LazyException(Error.NETWORK_ERROR, e)
                    return ex
                }
                is javax.net.ssl.SSLException -> {
                    ex = LazyException(Error.SSL_ERROR, e)
                    return ex
                }
                is ConnectTimeoutException -> {
                    ex = LazyException(Error.TIMEOUT_ERROR, e)
                    return ex
                }
                is java.net.SocketTimeoutException -> {
                    ex = LazyException(Error.TIMEOUT_ERROR, e)
                    return ex
                }
                is java.net.UnknownHostException -> {
                    ex = LazyException(Error.TIMEOUT_ERROR, e)
                    return ex
                }
                is LazyException -> return it

                else -> {
                    ex = LazyException(Error.UNKNOWN, e)
                    return ex
                }
            }
        }

        ex = LazyException(Error.UNKNOWN, e)

        return ex
    }

}