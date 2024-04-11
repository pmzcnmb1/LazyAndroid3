package com.gh.lazy.net.net.utils

import com.google.gson.GsonBuilder


/**
 * @Description: JSON转实体
 * @Author: GaoHua
 * @CreateDate: 2021/4/14 19:58
 * @Version: 1.0
 */
object GSONHelper {
    fun <T> jsonToEntity(json: String?, entity: Class<T>): T {
        return GsonBuilder().create().fromJson(json, entity)
    }
}

