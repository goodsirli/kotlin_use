package com.example.kotlinfirstdemo.daemon.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.kotlinfirstdemo.util.LogUtil
import java.util.concurrent.Executors

/**
 * 测试使用alarmcolck 来唤醒app
 */
class SingleTestAlarmService :Service(){

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val exeService = Executors.newFixedThreadPool(2)
        exeService.submit(runnable)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private companion object{
        var runnable = Runnable {
            kotlin.run {

                for (index in 0..300) {
                    LogUtil.showI(Thread.currentThread().name+ index.toString())
                    Thread.sleep(2000)
                }
            }
        }
    }

}