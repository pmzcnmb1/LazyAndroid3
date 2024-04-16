package com.gh.lazy.ui.base.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter4.BaseQuickAdapter
import com.chad.library.adapter4.viewholder.DataBindingHolder
import com.gh.lazy.utils.reflect.LazyReflectHelper

/**
 * BaseQuickAdapter 升级到4.x版本
 * 如果不稳定后续会更换
 * 支持viewBinding的BaseQuickAdapter
 */
abstract class LazyDataBindingAdapter<T : Any,DB: ViewDataBinding>:BaseQuickAdapter<T,DataBindingHolder<DB>>(){
    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int
    ): DataBindingHolder<DB> {
        val clazz = LazyReflectHelper.getClazz<Class<DB>>(this,1)
        val mBinding = clazz.getDeclaredMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        ).invoke(null, LayoutInflater.from(parent.context), parent, false) as DB
        return DataBindingHolder(mBinding)
    }

    override fun onBindViewHolder(holder: DataBindingHolder<DB>, position: Int, item: T?) {
        item?.let {
            holder.binding.run {
                bindingParams(item)
                executePendingBindings()
            }
        }
    }

    protected open fun DB.bindingParams(item:T?) {}
}