package com.example.kotlinfirstdemo.weakreference.thread

import com.example.kotlinfirstdemo.util.LogUtil
import java.util.concurrent.TimeUnit

class NoWeakReferenceThread :Thread {

    private var mIsRunning :Boolean ?= true

    private val TAG :String = "NoWeakReferenceThread"
    private var mList : List<String> ?= null
    constructor(list:List<String>){
        this.mList = list
    }

    override fun run() {
        super.run()

        mList?.forEach {
            if (!mIsRunning!!) return
            LogUtil.showE("拿到的数据：" + it)
            TimeUnit.SECONDS.sleep(1)
        }
    }

    public fun close(){
        mIsRunning = false
    }
}