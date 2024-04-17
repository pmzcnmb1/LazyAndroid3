package com.gh.lazy.ui.base.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter4.BaseQuickAdapter
import com.chad.library.adapter4.viewholder.DataBindingHolder

/**
 * BaseQuickAdapter 升级到4.x版本
 * 如果不稳定后续会更换 非必要不考虑反射方式了
 * 例如这种方式:
 * val clazz = (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
 * val mBinding = clazz.getDeclaredMethod("inflate",LayoutInflater::class.java,ViewGroup::class.java,Boolean::class.java)
 * .invoke(null, LayoutInflater.from(parent.context), parent, false) as DB
 *
 * @author GaoHua
 */
abstract class LazyDataBindingAdapter<T : Any,DB: ViewDataBinding>(private var itemLayoutId:Int):BaseQuickAdapter<T,DataBindingHolder<DB>>(){
    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int
    ): DataBindingHolder<DB> {
        return DataBindingHolder(itemLayoutId,parent)
    }

    override fun onBindViewHolder(holder: DataBindingHolder<DB>, position: Int, item: T?) {
        item?.let {
            holder.binding.run {
                bindData(holder,item,position)
                executePendingBindings()
            }
        }
    }
    protected abstract fun bindData(holder:DataBindingHolder<DB>,item: T?,position:Int)
}