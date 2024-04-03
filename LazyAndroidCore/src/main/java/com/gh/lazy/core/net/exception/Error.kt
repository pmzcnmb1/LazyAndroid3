package com.gh.lazy.core.net.exception


/**
 * @author GaoHua
 * 错误枚举
 */
enum class Error(private val code: Int, private val err: String) {

    /**
     * 未知错误
     */
    UNKNOWN(1000, "获取数据异常，请稍后再试"),
    /**
     * 解析错误
     */
    PARSE_ERROR(1001, "解析错误，请稍后再试"),
    /**
     * 网络错误
     */
    NETWORK_ERROR(1002, "网络连接错误，请稍后重试"),

    /**
     * 证书出错
     */
    SSL_ERROR(1004, "证书出错，请关闭您的加速器软件后稍后再试"),

    /**
     * 连接超时
     */
    TIMEOUT_ERROR(1006, "您当前的网络较差，请稍后重试"),

    /**
     * 解析错误
     */
    JSON_ERROR(1007, "加载中...");

    fun getValue(): String {
        return err
    }

    fun getKey(): Int {
        return code
    }

}