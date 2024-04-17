package com.gh.lazy.ui.base.activity

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.gh.lazy.utils.reflect.LazyReflectHelper

/**
 * @author GaoHua
 *  -----------------------------------------------------------------------------------
 *  放弃了使用反射的方式封装ViewBinding初始化，也没麻烦多少(多写个XXXBinding.inflate)，
 *  当然，依旧保留反射版本的，大家自己选择吧。
 *  -----------------------------------------------------------------------------------
 *  主要考虑以下几点:
 *  1.可读性和可维护性
 *  2.编译时安全性
 *  3.性能 虽然 inflate 方法内部也使用了反射,但官方提供的ViewBinding，是在考虑性能和可靠性的基础上实现的（靠谱）
 *  -----------------------------------------------------------------------------------
 *  反射的优点:
 *  简化了样版代码，无需创建额外的方法来处理 ViewBinding 的初始化，代码结构更加简洁。
 *  对于一般应用的性能影响可能并不明显，因为 inflate 方法一般只会在 Activity 的生命周期中调用一次。
 *  缺点：
 *  反射调用通常会比直接调用方法具有更高的开销，包括方法搜索、解析和执行，可能会对性能产生一定影响。
 *  由于反射调用不会在编译时捕获错误，因此存在在调用时传入错误的参数或调用不存在的方法的风险。
 *  -----------------------------------------------------------------------------------
 */
abstract class LazyVBActivity<VB : ViewBinding, VM : ViewModel> : LazyLogActivity() {

    private var _binding: VB? = null
    private var _viewModel: VM? = null
    protected val binding: VB
        get() = _binding ?: throw IllegalStateException("Binding is not initialized")

    protected val viewModel: VM
        get() = _viewModel ?: throw IllegalStateException("ViewModel is not initialized")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewBinding()
        _viewModel = createViewModel()
        setContentView(binding.root)
        init()
        onInitFinish()
    }

    private fun init() {
        loadData()
        addUiStateObs()
    }

    private fun createViewModel(): VM {
        return ViewModelProvider(this)[LazyReflectHelper.getClazz(this, index = 1)]
    }

    protected abstract fun getViewBinding(): VB
    override fun onRelease() {
        _binding = null
        _viewModel = null
    }
}