package com.example.kotlinfirstdemo.bean

import java.io.Serializable

class CustomViewBean :Serializable{

    var name :String?= null
    var type :Int ?= 0

    constructor(name: String?, type: Int?) {
        this.name = name
        this.type = type
    }

    constructor()

}