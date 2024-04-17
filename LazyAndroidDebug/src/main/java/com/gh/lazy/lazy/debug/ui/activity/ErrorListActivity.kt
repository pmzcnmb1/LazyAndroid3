package com.gh.lazy.lazy.debug.ui.activity

import com.gh.lazy.lazy.debug.databinding.ActivityErrorListBinding
import com.gh.lazy.ui.base.activity.LazyStaticActivity

class ErrorListActivity :LazyStaticActivity<ActivityErrorListBinding>(){
    override fun getViewBinding(): ActivityErrorListBinding {
       return ActivityErrorListBinding.inflate(layoutInflater)
    }

    override fun loadData() {

    }

    override fun addUiStateObs() {

    }

    override fun onInitFinish() {

    }
}