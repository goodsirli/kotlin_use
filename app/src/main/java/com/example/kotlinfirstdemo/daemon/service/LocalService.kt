package com.example.kotlinfirstdemo.daemon.service

import android.app.Service
import android.content.*
import android.os.IBinder
import android.os.RemoteException
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.kotlinfirstdemo.IMyAidlInterface
import com.example.kotlinfirstdemo.daemon.receiver.MyBroadCastReceiver
import com.example.kotlinfirstdemo.util.LogUtil
import com.example.kotlinfirstdemo.util.ToastUtil
import java.lang.reflect.Field
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


/**
 * 本地服务
 */
class LocalService : Service() {

    private var mBinder : MyBinder ?= null
    private var mIsBinded :Boolean = false

    var mConnection: ServiceConnection = object:ServiceConnection{
        override fun onServiceDisconnected(name: ComponentName?) {
            ToastUtil.showLong("链接断开，重新启动 LocalService")
            mIsBinded = false
            startService(Intent(this@LocalService, RemoteService::class.java))
            unbindService(this)
            bindService(
                Intent(this@LocalService, RemoteService::class.java),
                this,
                Context.BIND_IMPORTANT
            )
            mIsBinded = true

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {

            mIsBinded = true
            val iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service)
            try {
                LogUtil.showE("connected with " + iMyAidlInterface.serviceName)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }

    }

    override fun onBind(intent: Intent?): IBinder? {
        mBinder = MyBinder()
        var name: String? = LocalService::class.simpleName
        return mBinder
    }

    override fun onCreate() {
        ToastUtil.showLong("LocalService 启动")
        startService(Intent(this@LocalService, RemoteService::class.java))

        if(!mIsBinded){
            bindService(
                    Intent(this@LocalService, RemoteService::class.java),
                    mConnection,
                    Context.BIND_IMPORTANT
            )
        }

        val service :ExecutorService = Executors.newFixedThreadPool(3)
        for (i in 0..300) {
            val runnable = Runnable {
                try {
                    Thread.sleep(2000)
                    LogUtil.showE("当前线程："+Thread.currentThread().name)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            service.execute(runnable)
        }

//        initDaemonReceiver()
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    private class MyBinder : IMyAidlInterface.Stub() {
        override fun getServiceName(): String {
            return LocalService::class.java.name
        }
    }


    fun initDaemonReceiver(){
        val manager:LocalBroadcastManager = LocalBroadcastManager.getInstance(applicationContext)
        if(!isRegister(manager,Intent.ACTION_TIME_TICK)){
            val filter = IntentFilter(Intent.ACTION_TIME_TICK)

            val receiver = MyBroadCastReceiver()
            registerReceiver(receiver, filter)
        }
    }

    private fun isRegister(manager: LocalBroadcastManager, action: String): Boolean {
        var isRegister = false
        try {
            val mReceiversField: Field = manager.javaClass.getDeclaredField("mReceivers")
            mReceiversField.setAccessible(true)
            //            String name = mReceiversField.getName();
            val mReceivers = mReceiversField.get(manager) as HashMap<BroadcastReceiver, ArrayList<IntentFilter>>
            for (key in mReceivers.keys) {
                val intentFilters = mReceivers[key]
                LogUtil.showE("Key: $key Value: $intentFilters")
                for (i in 0 until intentFilters!!.size) {
                    val intentFilter = intentFilters[i]
                    val mActionsField: Field = intentFilter.javaClass.getDeclaredField("mActions")
                    mActionsField.setAccessible(true)
                    val mActions = mActionsField.get(intentFilter) as ArrayList<String>
                    for (j in 0 until mActions.size) {
                        if (mActions[i].equals(action)) {
                            isRegister = true
                            break
                        }
                    }
                }
            }
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
        return isRegister
    }

}