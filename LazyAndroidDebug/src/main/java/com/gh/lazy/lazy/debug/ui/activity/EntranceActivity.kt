package com.gh.lazy.lazy.debug.ui.activity

import android.content.Context
import android.content.Intent
import com.gh.lazy.ui.base.activity.LazyStaticActivity
import com.gh.lazy.ui.base.manager.LazyPagePathLogManager
import com.gh.lazy.ui.databinding.ActivityEntranceBinding
import com.gh.lazy.utils.common.clickNoRepeat

/**
 * @author GaoHua
 * 入口界面
 * */
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
        binding.tvGoPageLog.clickNoRepeat {
             PagePathLogListActivity.start(this)
        }

        binding.tvGoExpLog.clickNoRepeat {
            ErrorListActivity.start(this)
        }
    }
}