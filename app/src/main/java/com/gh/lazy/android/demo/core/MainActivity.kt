package com.gh.lazy.android.demo.core

import android.util.Log
import com.gh.lazy.android.databinding.ActivityMainBinding
import com.gh.lazy.debug.ILogUserInfo
import com.gh.lazy.debug.LazyDebugTool
import com.gh.lazy.net.net.core.LazyNet
import com.gh.lazy.ui.base.activity.LazyVBActivity

class MainActivity : LazyVBActivity<ActivityMainBinding, DemoViewModel>() {

    init {
        LazyNet.getLazyNet().initialize("")
    }


    override fun loadData() {
        Log.d("d", viewModel.a.toString())
    }

    override fun addUiStateObs() {
        binding.main.setOnClickListener {

        }
    }

    override fun onInitFinish() {
        LazyDebugTool.openErrorLog().openHttpLog().initialize(this)
        LazyDebugTool.setCustomUserIdentification(object : ILogUserInfo {
            override fun getUserId(): String {
                return "111111"
            }

            override fun getUserAlias(): String {
                return "高骅"
            }

        })
    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}