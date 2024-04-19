package com.gh.lazy.lazy.debug.ui.activity

import android.content.Context
import android.content.Intent
import com.gh.lazy.lazy.debug.databinding.ActivityPagePathBinding
import com.gh.lazy.ui.base.activity.LazyStaticActivity
import com.gh.lazy.ui.base.manager.LazyPagePathLogManager
import com.gh.lazy.utils.common.clickNoRepeat

class PagePathLogListActivity :LazyStaticActivity<ActivityPagePathBinding>(){

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val intent = Intent(context, PagePathLogListActivity::class.java)
            context.startActivity(intent)
        }
    }
    override fun getViewBinding(): ActivityPagePathBinding {
       return ActivityPagePathBinding.inflate(layoutInflater)
    }

    override fun loadData() {

    }

    override fun addUiStateObs() {

    }

    override fun onResume() {
        super.onResume()
        binding.tvPagePath.text= LazyPagePathLogManager.getLog()
        binding.tvPagePath.clickNoRepeat {
            LazyPagePathLogManager.clearLog()
        }
    }

    override fun onInitFinish() {

    }
}