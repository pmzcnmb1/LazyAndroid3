package com.gh.lazy.core.base.fragment

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import androidx.fragment.app.Fragment


/**
 * OnAttach -> OnCreate -> onInflate -> onGetLayoutInflater ->
 * OnCreateView -> onViewCreated -> OnActivityCreated ->
 * OnViewStateRestored -> OnStart -> OnResume
 */
abstract class LazyBaseFragment :Fragment(){

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
    }
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
    }
}