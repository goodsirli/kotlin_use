package com.example.kotlinfirstdemo.app

import android.content.Intent
import android.content.IntentFilter
import android.database.sqlite.SQLiteDatabase
import androidx.multidex.MultiDexApplication
import com.example.kotlinfirstdemo.daemon.receiver.MyBroadCastReceiver
import com.example.kotlinfirstdemo.dagger2.AppComponent
import com.example.kotlinfirstdemo.dagger2.AppModule
import com.example.kotlinfirstdemo.dagger2.DaggerAppComponent
import com.example.kotlinfirstdemo.dagger2.NetModule
import com.greendao.gen.DaoMaster
import com.greendao.gen.DaoSession


class WorkApplication : MultiDexApplication() {

    var mAppComponent: AppComponent?= null
    var  baseUl = "https://api.github.com";

    override fun onCreate() {
        super.onCreate()

        mWorkApplication = this

//        testDaemonReceiver()
        testDagger2()
        testGreenDao()
    }

    fun testDagger2(){
        mAppComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this   ))
            .netModule(NetModule(baseUl))
            .build()
    }

    fun testGreenDao(){
        setDataBase()
    }

    fun getAppComponent():AppComponent?{
        return mAppComponent
    }

    fun testDaemonReceiver(){
        val filter = IntentFilter(Intent.ACTION_TIME_TICK)

        val receiver = MyBroadCastReceiver()
        registerReceiver(receiver, filter)
    }

    companion object{
        var mWorkApplication:WorkApplication?= null

        fun getApplication(): WorkApplication? {
            return mWorkApplication
        }
    }

    var mHelper:DaoMaster.DevOpenHelper?= null

    var mDb : SQLiteDatabase?= null
    var mMaster:DaoMaster?= null
    var mDaoSession:DaoSession?= null

    fun setDataBase(){
        mHelper = DaoMaster.DevOpenHelper(this,"users-db",null)
        mDb = mHelper?.writableDatabase
        mMaster = DaoMaster(mDb)
        mDaoSession = mMaster?.newSession()

    }

    fun getDaoSession():DaoSession?{
        return mDaoSession
    }

    fun getDb():SQLiteDatabase?{
        return mDb
    }

}