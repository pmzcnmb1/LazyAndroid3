package com.gh.lazy.debug.ui.activity

import android.content.Context
import android.content.Intent
import com.gh.lazy.debug.R
import com.gh.lazy.debug.databinding.ActivityErrorListBinding
import com.gh.lazy.debug.ui.adapter.ErrorListAdapter
import com.gh.lazy.debug.utils.ErrorLogUtil
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
        ErrorListAdapter()
    }

    private val adapterData by lazy {
        ErrorLogUtil.getInstance().errorLogList?: mutableListOf()
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

        listAdapter.submitList(adapterData)
        listAdapter.addOnItemChildClickListener(R.id.cl_root) { _, _, position ->
            ErrorInfoActivity.start(this,adapterData[position])
        }
    }

}