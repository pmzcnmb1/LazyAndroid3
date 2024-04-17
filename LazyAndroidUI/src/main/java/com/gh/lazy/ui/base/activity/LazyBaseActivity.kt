package com.gh.lazy.ui.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gh.lazy.ui.base.manager.LazyActivityStackManager

abstract class LazyBaseActivity :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LazyActivityStackManager.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        onRelease()
        LazyActivityStackManager.finishActivity(this)
    }

    protected abstract fun loadData()
    protected abstract fun addUiStateObs()
    protected abstract fun onRelease()
    protected abstract fun onInitFinish()

}