package com.example.kotlinfirstdemo.bean

class FirstFragmentBean {
    var name:String? =null
    var age:Int?=0
    var weight:Int?=100

    constructor(name:String,age: Int,weight:Int){
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    constructor()



}