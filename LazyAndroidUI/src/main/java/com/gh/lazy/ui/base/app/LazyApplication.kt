package com.gh.lazy.ui.base.app

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.gh.lazy.ui.base.liftcycle.LazyAppLifecycle
import com.tencent.mmkv.MMKV

class LazyApplication : Application(), ViewModelStoreOwner {


    private var mFactory: ViewModelProvider.Factory? = null
    override val viewModelStore: ViewModelStore
        get() = ViewModelStore()

    override fun onCreate() {
        super.onCreate()
        MMKV.initialize(this)
        ProcessLifecycleOwner.get().lifecycle.addObserver(LazyAppLifecycle)
    }

    protected fun initGlobalViewModel(viewModel: Class<ViewModel>): ViewModel {
        return ViewModelProvider(this, this.getAppFactory())[viewModel]
    }

    private fun getAppFactory(): ViewModelProvider.Factory {
        if (mFactory == null) {
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(this)
        }
        return mFactory as ViewModelProvider.Factory
    }
}