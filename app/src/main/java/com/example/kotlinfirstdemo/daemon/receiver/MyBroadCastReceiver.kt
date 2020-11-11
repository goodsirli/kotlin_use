package com.example.kotlinfirstdemo.daemon.receiver

import android.app.ActivityManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.kotlinfirstdemo.app.WorkApplication
import com.example.kotlinfirstdemo.daemon.service.LocalService
import com.example.kotlinfirstdemo.daemon.service.SingleTestAlarmService
import com.example.kotlinfirstdemo.dagger2.AppModule
import com.example.kotlinfirstdemo.dagger2.DaggerAppComponent
import com.example.kotlinfirstdemo.util.LogUtil
import javax.inject.Inject

/**
 * 双进程守护广播接收器
 */
class MyBroadCastReceiver : BroadcastReceiver() {

    var mIsServiceRunning:Boolean = false

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action.equals(Intent.ACTION_TIME_TICK)){
            //检查service 状态
            val manager :ActivityManager = WorkApplication.getApplication()?.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            manager.getRunningServices(Integer.MAX_VALUE).forEach {
                if ("com.example.kotlinfirstdemo.daemon.service.LocalService".equals(it.service.className)){
                    mIsServiceRunning = true
                    LogUtil.showE("MyBroadCastReceiver:1")
                }

                if (!mIsServiceRunning){
                    LogUtil.showE("MyBroadCastReceiver:2")
//                    val intent2 : Intent = Intent(context,LocalService::class.java)
//                    context?.startService(intent2)

                    val intent2 : Intent = Intent(context,SingleTestAlarmService::class.java)
                    context?.startService(intent2)
                }

            }
        }
    }
}