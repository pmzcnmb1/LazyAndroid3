package com.gh.lazy.debug.ui.activity

import android.content.Context
import android.content.Intent
import com.gh.lazy.debug.databinding.ActivityErrorInfoBinding
import com.gh.lazy.debug.model.ErrorLogModel
import com.gh.lazy.ui.base.activity.LazyStaticActivity

class ErrorInfoActivity : LazyStaticActivity<ActivityErrorInfoBinding>() {

    private val errorInfo:ErrorLogModel by lazy {
        intent.getSerializableExtra("data") as ErrorLogModel
    }

    companion object {
        @JvmStatic
        fun start(context: Context, data: ErrorLogModel) {
            val intent = Intent(context, ErrorInfoActivity::class.java)
            intent.putExtra("data", data)
            context.startActivity(intent)
        }
    }

    override fun getViewBinding(): ActivityErrorInfoBinding {
        return ActivityErrorInfoBinding.inflate(layoutInflater)
    }

    override fun loadData() {

    }

    override fun addUiStateObs() {

    }

    override fun onInitFinish() {
        binding.tvPagePath.text =errorInfo.logContent
    }
}