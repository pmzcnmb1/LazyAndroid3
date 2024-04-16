package com.gh.lazy.lazy.debug.ui

import android.content.Context
import android.content.Intent
import com.gh.lazy.ui.base.activity.LazyStaticActivity
import com.gh.lazy.ui.databinding.ActivityEntranceBinding

class EntranceActivity :LazyStaticActivity<ActivityEntranceBinding>(){

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val intent = Intent(context, EntranceActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getViewBinding(): ActivityEntranceBinding {
       return ActivityEntranceBinding.inflate(layoutInflater)
    }

    override fun loadData() {

    }

    override fun addUiStateObs() {

    }

    override fun onInitFinish() {

    }
}