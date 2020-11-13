package com.example.kotlinfirstdemo.bean

/**
 * 测试RxBus
 */
class EventBeanRxBus {
    var name:String ?= null
    var type:Int ?= null

    constructor()

    constructor(name: String?, type: Int?) {
        this.name = name
        this.type = type
    }

}