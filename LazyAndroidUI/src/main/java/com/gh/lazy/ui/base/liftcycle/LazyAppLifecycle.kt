package com.gh.lazy.ui.base.liftcycle

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.gh.lazy.ui.base.manager.LazyPagePathLogManager

object LazyAppLifecycle :DefaultLifecycleObserver{

    private var isFirstLoad=true
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        LazyPagePathLogManager.addLaunchAppLog("App")
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)

        if (!isFirstLoad){
            LazyPagePathLogManager.addAppEnterForeground("App")
        }
        isFirstLoad=false
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        LazyPagePathLogManager.addAppEnterBackground("App")
    }
}