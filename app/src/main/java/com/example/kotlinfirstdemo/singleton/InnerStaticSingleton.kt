package com.example.kotlinfirstdemo.singleton

/**
 * 静态内部类
 */
class InnerStaticSingleton {
    private constructor(){}

    companion object{
        private var INSTANCE = InnerStaticSingleton()
        fun getInstance(): InnerStaticSingleton {
            return INSTANCE
        }
    }
}