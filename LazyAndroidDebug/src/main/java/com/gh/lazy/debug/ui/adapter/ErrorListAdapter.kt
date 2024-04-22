package com.gh.lazy.debug.ui.adapter

import com.chad.library.adapter4.viewholder.DataBindingHolder
import com.gh.lazy.debug.LazyDebugTool
import com.gh.lazy.debug.R
import com.gh.lazy.debug.databinding.ItemErrorLogBinding
import com.gh.lazy.debug.model.ErrorLogModel
import com.gh.lazy.ui.base.adapter.LazyDataBindingAdapter
import com.tencent.mmkv.MMKV

class ErrorListAdapter :
    LazyDataBindingAdapter<ErrorLogModel, ItemErrorLogBinding>(R.layout.item_error_log) {
    override fun bindData(
        holder: DataBindingHolder<ItemErrorLogBinding>,
        item: ErrorLogModel?,
        position: Int
    ) {
        holder.binding.apply {
            tvTitle.text = item?.title
            tvUser.text = "用户别名:".plus(
                MMKV.defaultMMKV().decodeString(LazyDebugTool.CUSTOM_USER_ALIAS, "UnKnowUser")
            ).plus("\n").plus("用户id:")
                .plus(MMKV.defaultMMKV().decodeString(LazyDebugTool.CUSTOM_USER_ID, "UnKnowID"))
                .plus("\n").plus(item?.time)
        }
    }
}