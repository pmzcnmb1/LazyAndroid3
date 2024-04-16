package com.gh.lazy.android.demo.core

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.gh.lazy.android.R
import com.gh.lazy.android.databinding.ActivityMainBinding
import com.gh.lazy.lazy.debug.LazyDebugTool
import com.gh.lazy.net.net.core.LazyNet
import com.gh.lazy.ui.base.activity.LazyVBActivity

class MainActivity : LazyVBActivity<ActivityMainBinding,DemoViewModel>() {

    init {
        LazyNet.getLazyNet().initialize("")
    }


    override fun loadData() {
       Log.d("d", viewModel.a.toString())
    }

    override fun addUiStateObs() {
        binding.main.setOnClickListener {

        }
    }

    override fun onInitFinish() {
       LazyDebugTool.initialize(this)
    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}