package com.gh.lazy.ui.base.liftcycle

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.gh.lazy.ui.base.manager.LazyPagePathLogManager

object LazyPageLifecycle: DefaultLifecycleObserver {

    private var isFirstLoad=true

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        LazyPagePathLogManager.addPageVisitLog(owner::class.java.simpleName)
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)

        if (!isFirstLoad){
            LazyPagePathLogManager.addPageResumeLog(owner::class.java.simpleName)
        }

        isFirstLoad=false
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        LazyPagePathLogManager.addPagePauseLog(owner::class.java.simpleName)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        LazyPagePathLogManager.addPageCloseLog(owner::class.java.simpleName)
    }
}