package com.example.kotlinfirstdemo.net.manager

/**
 * 双重锁单例模式
 */
class NetWorkManager {

    constructor() {
    }

    companion object {
        var mInstance: NetWorkManager? = null

        fun getInstance(): NetWorkManager? {
            if (mInstance == null) {
                synchronized(NetWorkManager::class) {
                    if (mInstance == null) mInstance = NetWorkManager()
                }
            }
            return mInstance
        }
    }

}