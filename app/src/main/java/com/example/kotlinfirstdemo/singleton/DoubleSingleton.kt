package com.example.kotlinfirstdemo.singleton

/**
 * 双重锁单例
 */
class DoubleSingleton {
    private constructor(){
    }

    companion object{
        private var doubleSingleton : DoubleSingleton ?= null
        fun getInstance(): DoubleSingleton? {
            if (doubleSingleton == null){
                synchronized(DoubleSingleton::class.java){
                    if(doubleSingleton == null){
                        doubleSingleton = DoubleSingleton()
                    }
                }
            }

            return doubleSingleton
        }
    }
}