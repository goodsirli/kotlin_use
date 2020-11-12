package com.example.kotlinfirstdemo.activitymanager

import android.view.View
import com.example.kotlinfirstdemo.R
import com.example.kotlinfirstdemo.base.BaseActivity
import com.example.kotlinfirstdemo.databinding.ActivityTestUtil1Binding

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
    }

    override fun initData() {

    }
}