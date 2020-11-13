package com.example.kotlinfirstdemo.activitymanager

import android.view.View
import com.example.kotlinfirstdemo.R
import com.example.kotlinfirstdemo.base.BaseActivity
import com.example.kotlinfirstdemo.bean.EventBeanRxBus
import com.example.kotlinfirstdemo.databinding.ActivityTestUtil1Binding
import com.example.kotlinfirstdemo.rx.RxBus

class TestActivityUtilActivity3 : BaseActivity<ActivityTestUtil1Binding>() {
    override fun initLayout(): Int {
        return R.layout.activity_test_util1
    }

    override fun initView() {
        mBinding?.tvJump?.setText("关闭应用")
        mBinding?.tvJump?.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                ActivityWorkManager.getInstance().appExit()
            }
        })

        mBinding?.tvContent?.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                RxBus.getInstance()?.post(EventBeanRxBus("111的数据",111))
                RxBus.getInstance()?.post(EventBeanRxBus("222的数据",222))
            }
        })
    }

    override fun initData() {
    }
}