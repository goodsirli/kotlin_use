package com.example.kotlinfirstdemo.activitymanager

import android.content.Intent
import android.util.Log
import android.view.View
import com.example.kotlinfirstdemo.R
import com.example.kotlinfirstdemo.base.BaseActivity
import com.example.kotlinfirstdemo.bean.CustomViewBean
import com.example.kotlinfirstdemo.bean.EventBeanRxBus
import com.example.kotlinfirstdemo.databinding.ActivityTestUtil1Binding
import com.example.kotlinfirstdemo.rx.RxBus
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.internal.util.NotificationLite.accept
import io.reactivex.rxjava3.functions.Action
import org.reactivestreams.Subscription

class TestActivityUtilActivity2 : BaseActivity<ActivityTestUtil1Binding>() {

    override fun initLayout(): Int {
        return R.layout.activity_test_util1
    }

    override fun initView() {
        mBinding?.tvJump?.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                startActivity(Intent(this@TestActivityUtilActivity2,TestActivityUtilActivity3::class.java))
            }
        })
    }

    override fun initData() {
        initRxBus()
    }

    override fun onDestroy() {
        super.onDestroy()
        unBindRxBus()
    }

    private fun initRxBus() {
        registerRxBus(EventBeanRxBus::class.java,object:Consumer<EventBeanRxBus>{
            override fun accept(t: EventBeanRxBus?) {
                if(t?.type == 111){
                    mBinding?.tvContent?.text = "我收到了类型111的数据："+ t?.name
                }
            }
        })
    }

}