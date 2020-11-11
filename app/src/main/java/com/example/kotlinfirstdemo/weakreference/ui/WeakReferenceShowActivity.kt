package com.example.kotlinfirstdemo.weakreference.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinfirstdemo.R
import com.example.kotlinfirstdemo.weakreference.thread.NoWeakReferenceThread
import com.example.kotlinfirstdemo.weakreference.thread.WeakReferenceThread

/**
 * 测试软引用及线程内存泄露相关问题
 */
class WeakReferenceShowActivity : AppCompatActivity() {

    var mWeak: WeakReferenceThread?= null
    var mNoWeak: NoWeakReferenceThread?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weak_reference_show)
        test()
    }

    override fun onDestroy() {
        super.onDestroy()
        mWeak?.close()
    }

    fun test(){
        var list = listOf("哈哈", "哈哈1", "哈哈2", "哈哈3", "哈哈4", "哈哈5", "哈哈6", "哈哈7", "哈哈8", "哈哈9")

        mWeak =
            WeakReferenceThread(
                list
            )
        mNoWeak =
            NoWeakReferenceThread(
                list
            )

        mWeak?.start()
        mNoWeak?.start()
    }

}