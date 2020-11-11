package com.example.kotlinfirstdemo.weakreference.thread

import com.example.kotlinfirstdemo.util.LogUtil
import java.lang.ref.WeakReference
import java.util.concurrent.TimeUnit

class WeakReferenceThread : Thread {

    private var mIsRunning :Boolean ?= true

    private val TAG :String = "WeakReferenceThread"

    private var mWeakReference: WeakReference<List<String>>? = null

    constructor(list: List<String>) {
        mWeakReference = WeakReference(list)
    }

    override fun run() {
        val list = mWeakReference?.get()

        list?.forEach {

            if (!mIsRunning!!) return
            LogUtil.showE("拿到的数据：" + it)
            TimeUnit.SECONDS.sleep(1)
        }
        super.run()

    }

    public fun close(){
        mIsRunning = false
    }

}