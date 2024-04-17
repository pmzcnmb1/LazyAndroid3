package com.gh.lazy.ui.base.activity

import android.os.Bundle
import com.gh.lazy.ui.base.liftcycle.LazyPageLifecycle
abstract class LazyLogActivity : LazyBaseActivity() {

    open fun enablePagePathLog(): Boolean {
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (enablePagePathLog()) {
            lifecycle.addObserver(LazyPageLifecycle)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        if (enablePagePathLog()) {
            lifecycle.removeObserver(LazyPageLifecycle)
        }
    }
}