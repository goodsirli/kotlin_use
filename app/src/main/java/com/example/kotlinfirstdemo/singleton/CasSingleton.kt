package com.example.kotlinfirstdemo.singleton

import java.util.concurrent.atomic.AtomicReference


/**
 * cas 方式的单例
 * 好处：
 * 不需要使用传统的锁机制来保证线程安全,CAS是一种基于忙等待的算法,
 * 依赖底层硬件的实现,相对于锁它没有线程切换和阻塞的额外消耗,可以支持较大的并行度。
 * 缺点：
 *如果忙等待一直执行不成功(一直在死循环中),会对CPU造成较大的执行开销。
 * 另外，如果N个线程同时执行到singleton = new Singleton();的时候，会有大量对象创建，很可能导致内存溢出。
 */
class CasSingleton {
    companion object{
        private var INSTANCE = AtomicReference<CasSingleton>();

        fun getInstance(): CasSingleton? {
            while (true){
                var singleton = INSTANCE.get();
                if (null != singleton) {
                    return singleton;
                }

                singleton = CasSingleton();
                if (INSTANCE.compareAndSet(null, singleton)) {
                    return singleton;
                }
            }
        }

    }

    private constructor(){}
}