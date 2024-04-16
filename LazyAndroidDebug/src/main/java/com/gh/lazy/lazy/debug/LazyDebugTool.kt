package com.gh.lazy.lazy.debug

import android.app.Activity
import android.view.Gravity
import com.gh.lazy.lazy.debug.ui.EntranceActivity
import com.gh.lazy.lazy.debug.utils.ErrorLogUtil
import com.lzf.easyfloat.EasyFloat
import com.lzf.easyfloat.enums.ShowPattern
import com.lzf.easyfloat.enums.SidePattern

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
            .setGravity(Gravity.LEFT or Gravity.TOP, 0, 240)
            .setMatchParent(widthMatch = false, heightMatch = false)
            .setFilter(EntranceActivity::class.java)
            .registerCallback {
                createResult { _, _, view ->
                    view?.setOnClickListener {
                        EntranceActivity.start(activity)
                    }
                }
            }
            .show()
    }
}