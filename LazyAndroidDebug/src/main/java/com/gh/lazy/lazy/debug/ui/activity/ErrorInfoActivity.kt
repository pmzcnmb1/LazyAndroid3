package com.gh.lazy.lazy.debug.ui.activity

import com.gh.lazy.lazy.debug.databinding.ActivityErrorInfoBinding
import com.gh.lazy.lazy.debug.utils.ErrorLogUtil
import com.gh.lazy.ui.base.activity.LazyStaticActivity

class ErrorInfoActivity:LazyStaticActivity<ActivityErrorInfoBinding>() {
    override fun getViewBinding(): ActivityErrorInfoBinding {
         return ActivityErrorInfoBinding.inflate(layoutInflater)
    }

    override fun loadData() {

    }

    override fun addUiStateObs() {

    }

    override fun onInitFinish() {

    }
}