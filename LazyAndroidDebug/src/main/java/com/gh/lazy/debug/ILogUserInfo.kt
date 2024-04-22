package com.gh.lazy.debug

/**
 * 自定义的用户标识
 * @author GaoHua
 */
interface ILogUserInfo {

    /**
     * 自定义用户ID
     */
    fun getUserId():String

    /**
     * 自定义用户别名
     */
    fun getUserAlias():String
}