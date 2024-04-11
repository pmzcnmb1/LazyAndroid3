package com.gh.lazy.net.net.exception


/**
 * @author GaoHua
 * 错误枚举
 */
enum class Error(private val code: Int, private val err: String) {

    /**
     * 未知错误
     */
    UNKNOWN(1000, "未知异常，请稍后再试"),
    /**
     * 解析错误
     */
    PARSE_ERROR(1001, "解析错误，请稍后再试"),
    /**
     * 网络错误
     */
    HTTP_ERROR(1002, "服务器异常，请稍后重试"),

    /**
     * 证书出错
     */
    SSL_ERROR(1004, "证书出错，请关闭您的代理"),

    /**
     * 连接超时
     */
    TIMEOUT_ERROR(1006, "您当前的网络较差，请稍后重试"),

    /**
     *主机名解析异常
     * 1.DNS解析问题：当使用主机名连接到网络资源时，Java会依赖DNS（Domain Name System）来将主机名解析为IP地址。如果DNS服务器无法解析主机名
     * 2.网络连接问题： 如果计算机无法连接到网络，或者网络不稳定，可能导致无法解析主机名
     * 3.主机名拼写错误： 可能在代码中输入的主机名有误，或者主机名在DNS服务器上不存在
     */
    UNKNOWN_HOST_ERROR(1008,"主机名无法解析"),

    /**
     * 解析错误
     */
    JSON_ERROR(1007, "数据解析错误");


    fun getValue(): String {
        return err
    }

    fun getKey(): Int {
        return code
    }

}