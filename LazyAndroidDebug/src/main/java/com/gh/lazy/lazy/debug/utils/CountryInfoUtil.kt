package com.sneaker.debug_tools.utils

import android.content.Context
import android.net.wifi.WifiManager
import android.telephony.TelephonyManager
import java.util.*
import android.annotation.SuppressLint
import java.lang.NullPointerException
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import android.os.Build


class CountryInfoUtil {

    companion object {

        //默认设置
        fun getDefaultCountry(): String {
            return Locale.getDefault().country.lowercase()
        }

        //手机卡
        fun getSimCountryIso(context: Context): String{
            val telephonyManager =
                context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val simCountryIso = telephonyManager.simCountryIso
            return simCountryIso.lowercase()
        }

        //注册运营商
        fun getNetworkCountryIso(context: Context): String{
            val telephonyManager =
                context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val networkCountryIso = telephonyManager.networkCountryIso
            val networkOperatorName = telephonyManager.networkOperatorName
//            telephonyManager.cellLocation.toString()
            return networkCountryIso.lowercase() + " " + networkOperatorName
        }

        //尝试从TelephonyManager（从SIM或CDMA设备）获取国家代码
        fun getDeviceCountryCode(context: Context): String? {
            var countryCode: String?

            // try to get country code from TelephonyManager service
            val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            if (tm != null) {
                // query first getSimCountryIso()
                countryCode = tm.simCountryIso
                if (countryCode != null && countryCode.length == 2) return countryCode.lowercase()

                countryCode = if (tm.phoneType == TelephonyManager.PHONE_TYPE_CDMA) {
                    // special case for CDMA Devices
                    getCDMACountryIso()
                } else {
                    // for 3G devices (with SIM) query getNetworkCountryIso()
                    tm.networkCountryIso
                }
                if (countryCode != null && countryCode.length == 2) return countryCode.lowercase()
            }

            // if network country not available (tablets maybe), get country code from Locale class
            countryCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                context.resources.configuration.locales[0].country
            } else {
                context.resources.configuration.locale.country
            }
            return if (countryCode != null && countryCode.length == 2) countryCode.lowercase() else "us"

            // general fallback to "us"
        }


        @SuppressLint("PrivateApi")
        private fun getCDMACountryIso(): String? {
            try {
                // try to get country code from SystemProperties private class
                val systemProperties = Class.forName("android.os.SystemProperties")
                val get: Method = systemProperties.getMethod("get", String::class.java)

                // get homeOperator that contain MCC + MNC
                val homeOperator = get.invoke(
                    systemProperties,
                    "ro.cdma.home.operator.numeric"
                ) as String

                // first 3 chars (MCC) from homeOperator represents the country code
                val mcc = homeOperator.substring(0, 3).toInt()
                when (mcc) {
                    330 -> return "PR"
                    310 -> return "US"
                    311 -> return "US"
                    312 -> return "US"
                    316 -> return "US"
                    283 -> return "AM"
                    460 -> return "CN"
                    455 -> return "MO"
                    414 -> return "MM"
                    619 -> return "SL"
                    450 -> return "KR"
                    634 -> return "SD"
                    434 -> return "UZ"
                    232 -> return "AT"
                    204 -> return "NL"
                    262 -> return "DE"
                    247 -> return "LV"
                    255 -> return "UA"
                }
            } catch (ignored: ClassNotFoundException) {
            } catch (ignored: NoSuchMethodException) {
            } catch (ignored: IllegalAccessException) {
            } catch (ignored: InvocationTargetException) {
            } catch (ignored: NullPointerException) {
            }
            return null
        }

    }
}