package com.example.kotlinfirstdemo.util

import android.content.Context
import android.content.SharedPreferences

/**
 * SharePreferences 工具类
 */
class SpUtil {
    companion object{
        var PREFERENCE_NAME = "com_example_kotlin_sp"

        /**存储字符串 */
        fun putString(context: Context, key: String?, value: String?): Boolean? {
            val preferences: SharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, 0)
            val editor = preferences.edit()
            editor.putString(key, value)
            return editor.commit()
        }

        /**读取字符串 */
        fun getString(context: Context, key: String?): String? {
            return getString(context, key, null)
        }

        /**读取字符串（带默认值的） */
        fun getString(context: Context, key: String?, defaultValue: String?): String? {
            val preferences: SharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, 0)
            return preferences.getString(key, defaultValue)
        }

        /**存储整型数字 */
        fun putInt(context: Context, key: String?, value: Int): Boolean {
            val preferences: SharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, 0)
            val editor = preferences.edit()
            editor.putInt(key, value)
            return editor.commit()
        }

        /**读取整型数字 */
        fun getInt(context: Context, key: String?): Int {
            return getInt(context, key, -1)
        }

        /**读取整型数字（带默认值的） */
        fun getInt(context: Context, key: String?, defaultValue: Int): Int {
            val preferences: SharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, 0)
            return preferences.getInt(key, defaultValue)
        }

        /**存储长整型数字 */
        fun putLong(context: Context, key: String?, value: Long): Boolean {
            val preferences: SharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, 0)
            val editor = preferences.edit()
            editor.putLong(key, value)
            return editor.commit()
        }

        /**读取长整型数字 */
        fun getLong(context: Context, key: String?): Long {
            return getLong(context, key, -0x1)
        }

        /**读取长整型数字（带默认值的） */
        fun getLong(context: Context, key: String?, defaultValue: Long): Long {
            val preferences: SharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, 0)
            return preferences.getLong(key, defaultValue)
        }

        /**存储Float数字 */
        fun putFloat(context: Context, key: String?, value: Float): Boolean {
            val preferences: SharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, 0)
            val editor = preferences.edit()
            editor.putFloat(key, value)
            return editor.commit()
        }

        /**读取Float数字 */
        fun getFloat(context: Context, key: String?): Float {
            return getFloat(context, key, -1.0f)
        }

        /**读取Float数字（带默认值的） */
        fun getFloat(context: Context, key: String?, defaultValue: Float): Float {
            val preferences: SharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, 0)
            return preferences.getFloat(key, defaultValue)
        }

        /**存储boolean类型数据 */
        fun putBoolean(context: Context, key: String?, value: Boolean): Boolean {
            val preferences: SharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, 0)
            val editor = preferences.edit()
            editor.putBoolean(key, value)
            return editor.commit()
        }

        /**读取boolean类型数据 */
        fun getBoolean(context: Context, key: String?): Boolean {
            return getBoolean(context, key, false)
        }

        /**读取boolean类型数据（带默认值的） */
        fun getBoolean(context: Context, key: String?, defaultValue: Boolean): Boolean {
            val preferences: SharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, 0)
            return preferences.getBoolean(key, defaultValue)
        }

        /**清除数据 */
        fun clearPreferences(context: Context): Boolean {
            val pref: SharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, 0)
            val editor = pref.edit()
            editor.clear()
            return editor.commit()
        }
    }

}