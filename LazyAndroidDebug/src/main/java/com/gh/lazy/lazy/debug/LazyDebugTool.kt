package com.gh.lazy.lazy.debug

import android.app.Activity
import android.view.Gravity
import com.gh.lazy.lazy.debug.ui.activity.EntranceActivity
import com.gh.lazy.lazy.debug.ui.activity.PagePathLogListActivity
import com.gh.lazy.lazy.debug.utils.ApiLogHelper
import com.gh.lazy.lazy.debug.utils.ErrorLogUtil
import com.gh.lazy.net.net.interceptor.ILazyLogInfo
import com.gh.lazy.net.net.interceptor.LazyLogInterceptor
import com.lzf.easyfloat.EasyFloat
import com.lzf.easyfloat.enums.ShowPattern
import com.lzf.easyfloat.enums.SidePattern
import okhttp3.Request

object LazyDebugTool {

    fun initialize(activity: Activity) {
        initDebugFloat()
        createDebugFloat(activity)
    }

    private fun initDebugFloat() {
        ErrorLogUtil.getInstance().init()
    }

    private fun createDebugFloat(activity: Activity) {
        EasyFloat.with(activity)
            .setLayout(R.layout.float_app)
            .setShowPattern(ShowPattern.FOREGROUND)
            .setSidePattern(SidePattern.RESULT_HORIZONTAL)
            .setTag("testFloat")
            .setDragEnable(true)
            .setGravity(Gravity.START or Gravity.TOP, 0, 240)
            .setMatchParent(widthMatch = false, heightMatch = false)
            .setFilter(EntranceActivity::class.java, PagePathLogListActivity::class.java)
            .registerCallback {
                createResult { _, _, view ->
                    view?.setOnClickListener {
                        EntranceActivity.start(activity)
                    }
                }
            }
            .show()

        LazyLogInterceptor.setLogInfoInterface(LazyLogInfo())
    }
}