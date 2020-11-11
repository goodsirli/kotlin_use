package com.example.kotlinfirstdemo

import android.content.Intent
import android.view.View
import com.example.kotlinfirstdemo.base.BaseActivity
import com.example.kotlinfirstdemo.daemon.ui.DaemonShowActivity
import com.example.kotlinfirstdemo.dagger2.DaggerTestActivity
import com.example.kotlinfirstdemo.databinding.ActivityMainBinding
import com.example.kotlinfirstdemo.databinding.ActivityTestGreenDaoBinding
import com.example.kotlinfirstdemo.greendao.ui.TestGreenDaoActivity


class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun initLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        mBinding?.tvShowDaemon?.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                startActivity(Intent(this@MainActivity,DaemonShowActivity::class.java))
            }
        })

        mBinding?.tvShowDagger?.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                startActivity(Intent(this@MainActivity,DaggerTestActivity::class.java))
            }
        })

        mBinding?.tvShowGreenDao?.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                startActivity(Intent(this@MainActivity,TestGreenDaoActivity::class.java))
            }
        })
    }

    override fun initData() {

    }

}