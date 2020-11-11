package com.example.kotlinfirstdemo.view

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinfirstdemo.R
import com.example.kotlinfirstdemo.bean.CustomViewBean
import com.example.kotlinfirstdemo.view.RoundRingView

/**
 * 自定义view 显示页面
 */
class CustomViewShowActivity :AppCompatActivity(){

    var bean :CustomViewBean?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_show_view)

        bean = intent?.getSerializableExtra("customView") as CustomViewBean?
        val myname1 = bean?.name
        val myType1 = bean?.type

        var view = findViewById<RoundRingView>(R.id.roundView)
//        var view = RoundRingView(this)
        //环状百分比
        if(myType1 == 1){

            val colors = ArrayList<Int>()
            colors.add(Color.parseColor("#FF3700B3"))
            colors.add(Color.parseColor("#FFBB86FC"))
            colors.add(Color.parseColor("#FF03DAC5"))
            colors.add(Color.parseColor("#FF018786"))
            val datas = ArrayList<Float>()
            datas.add(2f)
            datas.add(3f)
            datas.add(2f)
            datas.add(4f)
            view.setData(datas,colors)
        }

//        setContentView(view)

    }

}