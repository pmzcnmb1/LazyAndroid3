package com.gh.lazy.ui.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class LazyBaseActivity :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityStackManager.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        onRelease()
        ActivityStackManager.finishActivity(this)
    }

    protected abstract fun loadData()
    protected abstract fun addUiStateObs()
    protected abstract fun onRelease()
    protected abstract fun onInitFinish()

}