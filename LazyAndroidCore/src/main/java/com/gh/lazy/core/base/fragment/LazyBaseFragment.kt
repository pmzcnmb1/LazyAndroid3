package com.gh.lazy.core.base.fragment

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


/**
 * OnAttach -> OnCreate -> onInflate -> onGetLayoutInflater ->
 * OnCreateView -> onViewCreated -> OnActivityCreated ->
 * OnViewStateRestored -> OnStart -> OnResume
 */
abstract class LazyBaseFragment :Fragment(){

     private var attachContext: Context? = null

     fun isShowFragmentLifeCycle(isShow:Boolean=true){

     }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onInflate(context: Context, attrs: AttributeSet, savedInstanceState: Bundle?) {
        super.onInflate(context, attrs, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        attachContext=context
    }
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun getContext(): Context? {
        return super.getContext()?:attachContext!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    abstract fun initView()

}