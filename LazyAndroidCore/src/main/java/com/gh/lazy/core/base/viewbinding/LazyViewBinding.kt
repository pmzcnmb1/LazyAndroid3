package com.gh.lazy.core.base.viewbinding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * viewBinding注入
 * @Author: GaoHua
 */
@JvmName("inflate")
fun <VB : ViewBinding> Any.inflateBinding(layoutInflater: LayoutInflater): VB =
        withBindingClass(this) { clazz ->
            clazz.getMethod("inflate", LayoutInflater::class.java).invoke(null, layoutInflater) as VB
        }

@JvmName("inflate")
fun <VB : ViewBinding> Any.inflateBinding(layoutInflater: LayoutInflater, parent: ViewGroup?, attachToParent: Boolean): VB =
        withBindingClass(this) { clazz ->
            clazz.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
                    .invoke(null, layoutInflater, parent, attachToParent) as VB
        }

@JvmName("inflate")
fun <VB : ViewBinding> Any.inflateBinding(parent: ViewGroup): VB =
        inflateBinding(LayoutInflater.from(parent.context), parent, false)

fun <VB : ViewBinding> Any.bindView(view: View): VB =
        withBindingClass(this) { clazz ->
            clazz.getMethod("bind", LayoutInflater::class.java).invoke(null, view) as VB
        }

private fun <VB : ViewBinding> withBindingClass(any: Any, block: (Class<VB>) -> VB): VB {
    any.allParameterizedType.forEach { parameterizedType ->
        parameterizedType.actualTypeArguments.forEach {
            try {
                return block.invoke(it as Class<VB>)
            } catch (e: Exception) {

            }
        }
    }
    throw IllegalArgumentException("请检查传入的ViewBinding泛型")
}

private val Any.allParameterizedType: List<ParameterizedType>
    get() {
        val genericParameterizedType = mutableListOf<ParameterizedType>()
        var genericSuperclass = javaClass.genericSuperclass
        var superclass = javaClass.superclass
        while (superclass != null) {
            if (genericSuperclass is ParameterizedType) {
                genericParameterizedType.add(genericSuperclass)
            }
            genericSuperclass = superclass.genericSuperclass
            superclass = superclass.superclass
        }
        return genericParameterizedType
    }