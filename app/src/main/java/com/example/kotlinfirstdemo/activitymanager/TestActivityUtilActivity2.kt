package com.example.kotlinfirstdemo.activitymanager

import android.content.Intent
import android.view.View
import com.example.kotlinfirstdemo.R
import com.example.kotlinfirstdemo.base.BaseActivity
import com.example.kotlinfirstdemo.databinding.ActivityTestUtil1Binding

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

    }
}