package com.gh.lazy.lazy.debug.ui.adapter

import com.chad.library.adapter4.viewholder.DataBindingHolder
import com.gh.lazy.lazy.debug.R
import com.gh.lazy.lazy.debug.databinding.ItemErrorLogBinding
import com.gh.lazy.ui.base.adapter.LazyDataBindingAdapter
import com.sneaker.debug_tools.bean.ErrorLogModel

class ErrorListAdapter :LazyDataBindingAdapter<ErrorLogModel,ItemErrorLogBinding>(R.layout.item_error_log) {
    override fun bindData(
        holder: DataBindingHolder<ItemErrorLogBinding>,
        item: ErrorLogModel?,
        position: Int
    ) {
        holder.binding.apply {

        }
    }
}