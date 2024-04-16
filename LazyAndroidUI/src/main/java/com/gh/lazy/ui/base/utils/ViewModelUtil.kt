package com.gh.lazy.ui.base.utils

import java.lang.reflect.ParameterizedType

/**
 *  获取当前类绑定的泛型ViewModel-clazz
 *  @Author: GaoHua
 *  @CreateDate: 2021/4/2 17:05
 */
fun <VM> getVmClazz(obj: Any): VM {
    return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
}

