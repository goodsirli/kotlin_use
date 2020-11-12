package com.example.kotlinfirstdemo.activitymanager

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import com.example.kotlinfirstdemo.app.WorkApplication
import com.example.kotlinfirstdemo.util.LogUtil
import java.lang.ref.WeakReference

/**
 *
 * 在BaseActivity 的onCreate方法中addActivity，在onDestroy方法中removeActivity
 *
 */
class ActivityWorkManager {

    constructor()

    companion object {
        //在关闭activity的时候是否杀掉进程，杀掉进程用户体验不太好会有黑色闪屏，
        // 默认为false,根据需要手动在这里修改
        private val KILL_PROCESS = false
        private val INSTANCE =
            ActivityWorkManager();
        private val mActivityList: ArrayList<Activity> = ArrayList()
        private var mWeakReferenceList: WeakReference<ArrayList<Activity>> =
            WeakReference(mActivityList)

        fun getInstance(): ActivityWorkManager {
            return INSTANCE;
        }
    }

    fun addActivity(activity: Activity) {
        mActivityList.add(activity);

        //如果appExit方法中关掉进程的方法注释掉，就会导致第二次进来的时候WeakReference
        // 没有和mActivityList 绑定，所以在这里重新判断绑定
        if (mWeakReferenceList.get() == null) {
            mWeakReferenceList = WeakReference(
                mActivityList
            )
        }

        logActivity()
    }

    fun removeActivity(activity: Activity) {
        mActivityList.remove(activity);
        logActivity()
    }

    /**
     * 结束所有Activity
     */
    private fun finishAllActivity() {
        val listActivity: ArrayList<Activity>? = mWeakReferenceList.get()

        listActivity?.forEach {
            it.finish()
            LogUtil.showE("关闭了Activity:" + it.toString())
        }
        mActivityList.clear()
        mWeakReferenceList.clear()
    }

    @SuppressLint("ServiceCast")
    fun appExit() {
        finishAllActivity()

        //下面这两行杀掉后台进程，如果有杀掉进程的需求可以调用，
        //但是退出程序时用户体验不太友好，会有黑色闪屏
        if (KILL_PROCESS) {
            val activityManager: ActivityManager = WorkApplication.getApplication()?.
            getSystemService( Context.ACTIVITY_SERVICE ) as ActivityManager
            activityManager.killBackgroundProcesses(  WorkApplication.getApplication()?.getPackageName() )
            System.exit(0);
        }
    }


    //打印日志
    fun logActivity() {
        val listActivity: ArrayList<Activity>? = mWeakReferenceList.get()

        if (listActivity == null) {
            LogUtil.showE("listActivity为空")
        } else {
//            LogUtil.showE("listActivity的size:" + listActivity.size)
        }
    }
}