package com.gh.lazy.lazy.debug.ui.activity

import android.content.Context
import android.content.Intent
import com.gh.lazy.lazy.debug.databinding.ActivityErrorListBinding
import com.gh.lazy.lazy.debug.ui.adapter.ErrorListAdapter
import com.gh.lazy.lazy.debug.utils.ErrorLogUtil
import com.gh.lazy.ui.base.activity.LazyStaticActivity

class ErrorListActivity :LazyStaticActivity<ActivityErrorListBinding>(){

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val intent = Intent(context, ErrorListActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val listAdapter by lazy {
        ErrorListAdapter(this,ErrorLogUtil.getInstance().errorLogList?: mutableListOf())
    }

    override fun getViewBinding(): ActivityErrorListBinding {
       return ActivityErrorListBinding.inflate(layoutInflater)
    }

    override fun loadData() {

    }

    override fun addUiStateObs() {

    }

    override fun onInitFinish() {
        binding.rvList.apply {
            adapter=listAdapter
        }
    }

}