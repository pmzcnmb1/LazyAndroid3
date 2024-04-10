package com.gh.lazy.core.base.viewmodel

import java.lang.reflect.ParameterizedType

/**
 * @Author: GaoHua
 * @CreateDate: 2021/4/2 17:05
 * 获取当前类绑定的泛型ViewModel-clazz
 */
fun <VM> getVmClazz(obj: Any): VM {
    return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
}

