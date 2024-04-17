package com.gh.lazy.ui.base.manager

import com.tencent.mmkv.MMKV
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * 记录页面访问路径
 * @author GaoHua
 */
object LazyPagePathLogManager {

    private const val LOG_KEY="PagePathLog"
    private val kv = MMKV.defaultMMKV()

    private fun saveLog(log: String) {
        val logs= getLog().plus("\n").plus(log)
        kv.encode(LOG_KEY,logs)
    }

    fun addPageVisitLog(pageName: String) {
        val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val log = "$pageName 创建: $timestamp"
        saveLog(log)
    }

    fun addLaunchAppLog(pageName: String){
        val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val log = "-------$pageName 启动: $timestamp-------"
        saveLog(log)
    }

    fun addAppEnterForeground(pageName: String){
        val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val log = "$pageName 回到前台: $timestamp"
        saveLog(log)
    }

    fun addAppEnterBackground(pageName: String){
        val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val log = "$pageName 切换到后台: $timestamp"
        saveLog(log)
    }
    fun addPagePauseLog(pageName: String){
        val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val log = "$pageName 暂停: $timestamp"
        saveLog(log)
    }

    fun addPageResumeLog(pageName: String){
        val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val log = "$pageName 恢复: $timestamp"
        saveLog(log)
    }
    fun addPageCloseLog(pageName: String) {
        val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val log = "$pageName 关闭: $timestamp"
        saveLog(log)
    }

    fun clearLog(){
        kv.removeValueForKey(LOG_KEY)
    }

    fun getLog():String{
        return kv.decodeString(LOG_KEY,"")?:""
    }
}