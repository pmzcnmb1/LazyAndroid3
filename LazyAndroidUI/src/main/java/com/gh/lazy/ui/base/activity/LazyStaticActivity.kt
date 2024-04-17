package com.gh.lazy.ui.base.activity

import android.os.Bundle
import androidx.viewbinding.ViewBinding
abstract class LazyStaticActivity<VB : ViewBinding> :LazyLogActivity() {
    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding ?: throw IllegalStateException("Binding is not initialized")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewBinding()
        setContentView(binding.root)
        init()
        onInitFinish()
    }
    private fun init() {
        loadData()
        addUiStateObs()
    }
    protected abstract fun getViewBinding(): VB
    override fun onRelease() {
        _binding = null
    }
}