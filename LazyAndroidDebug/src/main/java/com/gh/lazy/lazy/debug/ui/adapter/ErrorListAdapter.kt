package com.gh.lazy.lazy.debug.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.gh.lazy.lazy.debug.LazyDebugTool
import com.gh.lazy.lazy.debug.R
import com.gh.lazy.lazy.debug.model.ErrorLogModel
import com.tencent.mmkv.MMKV

class ErrorListAdapter (private val context: Context,private val data:List<ErrorLogModel>): RecyclerView.Adapter<ErrorListAdapter.ErrorListHolder> (){

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ErrorListHolder {
        val view = inflater.inflate(R.layout.item_error_log, parent, false)
        return ErrorListHolder(view)
    }

    override fun onBindViewHolder(holder: ErrorListHolder, position: Int) {
        val item = data[position]
        holder.tvTitle.text = item.title
        holder.tvUser.text = "当前用户信息--->".plus(MMKV.defaultMMKV().decodeString(LazyDebugTool.CUSTOM_USER_ALIAS,"UnKnowUser"))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ErrorListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: AppCompatTextView = itemView.findViewById(R.id.tv_title)
        val tvUser: AppCompatTextView = itemView.findViewById(R.id.tv_user)
    }
}