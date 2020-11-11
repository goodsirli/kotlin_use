package com.example.kotlinfirstdemo.util

import android.widget.Toast
import com.example.kotlinfirstdemo.app.WorkApplication

class ToastUtil {
    companion object{
        fun show(msg : String){
            Toast.makeText(WorkApplication.getApplication(),msg,Toast.LENGTH_SHORT).show()
        }

        fun showLong(msg:String){
            Toast.makeText(WorkApplication.getApplication(),msg,Toast.LENGTH_SHORT).show()
        }
    }
}