package com.gh.lazy.core.coroutine

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

class LazyCoroutineScope(
    val lifecycleOwner: LifecycleOwner? = null,
    val cancelLifeEvent: Lifecycle.Event = Lifecycle.Event.ON_DESTROY
) {

}