package com.gh.lazy.utils.info

import android.app.ActivityManager
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Build
import android.os.LocaleList
import android.telephony.TelephonyManager
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*


class LocalInfoUtil {

    companion object {
        private val sdf = SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.getDefault())
        private val sdf1 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())


        fun getMMDDYYYYHHMMss(time: Long): String? {
            try {
                return sdf1.format(time)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        private fun getPackageInfo(context: Context?): PackageInfo? {
            val pm = context?.packageManager
            var packageInfo: PackageInfo? = null
            try {
                packageInfo = pm?.getPackageInfo(context.packageName, 0)
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
            return packageInfo
        }

        fun getOsMemory(context: Context): String {
            val activityManager =
                context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val info = ActivityManager.MemoryInfo()
            activityManager.getMemoryInfo(info)

            val builder = StringBuilder()
            builder.append("系统内存总数：" + ((info.totalMem shr 10) / 1024) + "M" + "\n")
            builder.append("系统剩余内存：" + ((info.availMem shr 10) / 1024) + "M" + "\n")
            builder.append("系统是否处于低内存运行：" + info.lowMemory + "\n")
            builder.append("当系统剩余内存低于 " + ((info.threshold shr 10) / 1024) + "M 时就看成低内存运行")
            return builder.toString()
        }

        @JvmStatic
        fun getTimeZone(): Int {
            val tz = TimeZone.getDefault()
            return tz.rawOffset / 1000 / 3600
        }

        fun getAppMemory(context: Context): String {
            val activityManager =
                context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val info = ActivityManager.MemoryInfo()
            activityManager.getMemoryInfo(info)

            val builder = StringBuilder()
            builder.append("应用最大分配内存：" + activityManager.memoryClass + "M" + "\n")
            val totalMemory = (Runtime.getRuntime().totalMemory() * 1.0 / (1024 * 1024))
            builder.append("当前分配的总内存：${totalMemory}M\n")
            val freeMemory = (Runtime.getRuntime().freeMemory() * 1.0 / (1024 * 1024))
            builder.append("剩余可用内存：${freeMemory}M")
            return builder.toString()
        }

        fun getAppName(context: Context?): String {
            val title = "应用名称："
            val result =
                getPackageInfo(context)?.applicationInfo?.loadLabel(context!!.packageManager)
            return title + result
        }

        fun getAppPackName(context: Context): String {
            val title = "应用包名："
            val result = getPackageInfo(context)?.packageName
            return title + result
        }

        fun getAppVersion(context: Context): String {
            val title = "当前版本：v"
            val result = getPackageInfo(context)?.versionName
            return title + result
        }

        fun getAppVersionWithoutPrefix(context: Context): String? {
            return getPackageInfo(context)?.versionName
        }

        fun getDeviceName(context: Context): String {
            val title = "手机系统版本：Android "
            val result = Build.VERSION.RELEASE
            return title + result
        }

        fun getDeviceName(): String {
            return Build.PRODUCT
        }

        fun getDeviceBrand(context: Context): String {
            val title = "手机厂商："
            return title + Build.BRAND
        }

        fun getSystemModel(context: Context): String {
            val title = "手机型号："
            return title + Build.MODEL
        }

        fun getFirstInstallTime(context: Context): String {
            val title = "第一次安装时间："
            val result = getMMDDYYYYHHMMss(getPackageInfo(context)?.firstInstallTime ?: 0)
            return title + result
        }

        fun getLastUpdateTime(context: Context): String {
            val title = "最后一次更新时间："
            val result = getMMDDYYYYHHMMss(getPackageInfo(context)?.lastUpdateTime ?: 0)
            return title + result
        }

        fun getNetWorkType(context: Context): String {
            var type = ""
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = cm.activeNetworkInfo
            if (info == null) {
                type = "null(无网络)"
            } else if (info.type == ConnectivityManager.TYPE_WIFI) {
                type = "wlan(WIFI)"
            } else if (info.type == ConnectivityManager.TYPE_MOBILE) {
                val subType = info.subtype
                type =
                    when (subType) {
                        TelephonyManager.NETWORK_TYPE_CDMA, TelephonyManager.NETWORK_TYPE_GPRS, TelephonyManager.NETWORK_TYPE_EDGE,
                        TelephonyManager.NETWORK_TYPE_1xRTT, TelephonyManager.NETWORK_TYPE_IDEN -> "2g"
                        TelephonyManager.NETWORK_TYPE_UMTS, TelephonyManager.NETWORK_TYPE_HSDPA, TelephonyManager.NETWORK_TYPE_HSUPA,
                        TelephonyManager.NETWORK_TYPE_HSPA, TelephonyManager.NETWORK_TYPE_EVDO_A, TelephonyManager.NETWORK_TYPE_EVDO_0,
                        TelephonyManager.NETWORK_TYPE_EVDO_B, TelephonyManager.NETWORK_TYPE_EHRPD, TelephonyManager.NETWORK_TYPE_HSPAP,
                        TelephonyManager.NETWORK_TYPE_TD_SCDMA -> "3g"
                        TelephonyManager.NETWORK_TYPE_LTE -> "4g"
                        TelephonyManager.NETWORK_TYPE_UNKNOWN -> "cellular(流量)"
                        else -> "cellular(流量)"
                    }
            }
            val title = "当前使用的网络类型："
            return title + type
        }


    }
}