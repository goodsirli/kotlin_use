package com.example.kotlinfirstdemo.daemon.service

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.os.RemoteException
import com.example.kotlinfirstdemo.IMyAidlInterface
import com.example.kotlinfirstdemo.util.LogUtil
import com.example.kotlinfirstdemo.util.ToastUtil

/**
 * 远程服务
 */
class RemoteService : Service() {
    var TAG :String = RemoteService::class.simpleName.toString()
    private var mBinder: MyBinder? = null

    private val connection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val iMyAidlInterface: IMyAidlInterface = IMyAidlInterface.Stub.asInterface(service)
            try {
                LogUtil.showE("connected with " + iMyAidlInterface.serviceName)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }

        override fun onServiceDisconnected(name: ComponentName) {
            ToastUtil.showLong("链接断开，重新启动 LocalService")
            startService(Intent(this@RemoteService, LocalService::class.java))
            bindService(
                Intent(this@RemoteService, LocalService::class.java),
                this,
                Context.BIND_IMPORTANT
            )
        }
    }

    fun RemoteService() {}

    override fun onCreate() {
        super.onCreate()
        ToastUtil.showLong("RemoteService 启动")
        bindService(Intent(this, LocalService::class.java), connection, Context.BIND_IMPORTANT)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        mBinder =MyBinder()
        return mBinder
    }

    private class MyBinder : IMyAidlInterface.Stub() {
        override fun getServiceName(): String {
            return RemoteService::class.java.name
        }
    }
}