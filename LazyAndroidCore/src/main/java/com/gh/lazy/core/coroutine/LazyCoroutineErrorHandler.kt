package com.gh.lazy.core.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class LazyCoroutineErrorHandler (){
    val scope = CoroutineScope(Job() + Dispatchers.Main)

}