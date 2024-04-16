package com.gh.lazy.ui.base.activity

import androidx.activity.ComponentActivity
import androidx.fragment.app.FragmentActivity
import java.util.Stack

/**
 * activity堆栈管理
 */
object  ActivityStackManager  {
    private val mActivityStack = Stack<FragmentActivity>()

    fun addActivity(activity: FragmentActivity) {
        mActivityStack.push(activity)
    }

    fun removeActivity(activity: FragmentActivity) {
        mActivityStack.remove(activity)
    }

    fun getCurrentActivity(): FragmentActivity? {
        return if (mActivityStack.isNotEmpty()) mActivityStack.peek() else null
    }

    fun getCurrentComponentActivity(): ComponentActivity? {
        return if (mActivityStack.isNotEmpty()) mActivityStack.peek() else null
    }

    fun finishAllActivity() {
        while (mActivityStack.isNotEmpty()) {
            val activity = mActivityStack.pop()
            if (activity != null && !activity.isFinishing) {
                activity.finish()
            }
        }
    }

    fun finishActivity(cls: Class<out FragmentActivity>) {
        for (activity in mActivityStack) {
            if (activity.javaClass == cls) {
                finishActivity(activity)
                break
            }
        }
    }

    fun checkActivity(cls: Class<out FragmentActivity>): Boolean {
        for (activity in mActivityStack) {
            if (activity.javaClass == cls) {
                return true
            }
        }
        return false
    }

    fun findActivity(cls: Class<out FragmentActivity>): FragmentActivity? {
        for (activity in mActivityStack) {
            if (activity.javaClass == cls) {
                return activity
            }
        }
        return null
    }

    fun finishActivity(activity: FragmentActivity?) {
        activity?.let {
            mActivityStack.remove(it)
            if (!it.isFinishing) {
                it.finish()
            }
        }
    }

    fun finishToActivity(actCls: Class<out FragmentActivity>, isIncludeSelf: Boolean): Boolean {
        val buf = mutableListOf<FragmentActivity>()
        val size = mActivityStack.size
        for (i in size - 1 downTo 0) {
            val activity = mActivityStack[i]
            if (activity.javaClass.isAssignableFrom(actCls)) {
                for (a in buf) {
                    a.finish()
                }
                return true
            } else if (i == size - 1 && isIncludeSelf) {
                buf.add(activity)
            } else if (i != size - 1) {
                buf.add(activity)
            }
        }
        return false
    }

    fun finishOtherActivity(vararg actCls: Class<out FragmentActivity>) {
        val buf = mutableListOf<FragmentActivity>()
        val size = mActivityStack.size
        for (i in size - 1 downTo 0) {
            val activity = mActivityStack[i]
            for (actCl in actCls) {
                if (activity.javaClass != actCl) {
                    buf.add(activity)
                }
            }
        }
        for (activity in buf) {
            finishActivity(activity)
        }
    }

    fun isEmptyStack(): Boolean {
        return mActivityStack.isEmpty()
    }

    fun stackSize(): Int {
        return mActivityStack.size
    }
}
