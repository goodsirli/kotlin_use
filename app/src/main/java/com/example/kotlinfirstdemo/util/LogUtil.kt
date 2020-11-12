package com.example.kotlinfirstdemo.util

import android.util.Log
import com.example.kotlinfirstdemo.BuildConfig

class LogUtil {
    companion object {

        fun showI(msg: String) {

            if (BuildConfig.DEBUG) {
                var className = Exception().stackTrace[1].className

                //只打印类名，去掉包名和方法名
                val list: List<String> = className?.split("$")!!
                var temp: String? = null
                if (list.size > 0) {
                    temp = list[0]
                    val list2: List<String> = temp.split(".")
                    if (list2.size > 0) className = list2[list2.size - 1]
                }
                Log.i(className, msg)
            }

        }

        fun showD(msg: String) {
            if (BuildConfig.DEBUG) {
                var className = Exception().stackTrace[1].className

                //只打印类名，去掉包名和方法名
                val list: List<String> = className?.split("$")!!
                var temp: String? = null
                if (list.size > 0) {
                    temp = list[0]
                    val list2: List<String> = temp.split(".")
                    if (list2.size > 0) className = list2[list2.size - 1]
                }
                Log.d(className, msg)
            }
        }

        fun showE(msg: String) {
            if (BuildConfig.DEBUG) {
                var className = Exception().stackTrace[1].className

                //只打印类名，去掉包名和方法名
                val list: List<String> = className?.split("$")!!
                var temp: String? = null
                if (list.size > 0) {
                    temp = list[0]
                    val list2: List<String> = temp.split(".")
                    if (list2.size > 0) className = list2[list2.size - 1]
                }
                Log.e(className, msg)
            }
        }

        fun showW(msg: String) {
            if (BuildConfig.DEBUG) {
                var className = Exception().stackTrace[1].className

                //只打印类名，去掉包名和方法名
                val list: List<String> = className?.split("$")!!
                var temp: String? = null
                if (list.size > 0) {
                    temp = list[0]
                    val list2: List<String> = temp.split(".")
                    if (list2.size > 0) className = list2[list2.size - 1]
                }
                Log.w(className, msg)
            }
        }
    }
}