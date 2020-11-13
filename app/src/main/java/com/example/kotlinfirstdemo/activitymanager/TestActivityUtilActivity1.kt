package com.example.kotlinfirstdemo.activitymanager

import android.content.Intent
import android.view.View
import com.example.kotlinfirstdemo.R
import com.example.kotlinfirstdemo.base.BaseActivity
import com.example.kotlinfirstdemo.bean.EventBeanRxBus
import com.example.kotlinfirstdemo.databinding.ActivityTestUtil1Binding
import io.reactivex.functions.Consumer

class TestActivityUtilActivity1 : BaseActivity<ActivityTestUtil1Binding>() {

    override fun initLayout(): Int {
        return R.layout.activity_test_util1
    }

    override fun initView() {
        mBinding?.tvJump?.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View?) {
                startActivity(Intent(this@TestActivityUtilActivity1,TestActivityUtilActivity2::class.java))
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
        registerRxBus(EventBeanRxBus::class.java,object: Consumer<EventBeanRxBus> {
            override fun accept(t: EventBeanRxBus?) {
                if(t?.type == 111){
                    mBinding?.tvContent?.text = "我收到了类型111的数据："+ t?.name
                }else if(t?.type == 222){
                    mBinding?.tvContent?.text = "我收到了类型222的数据" + t?.name
                }
            }
        })
    }

}