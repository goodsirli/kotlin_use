package com.example.kotlinfirstdemo.daemon.ui

import android.content.Intent
import android.view.View
import com.example.kotlinfirstdemo.R
import com.example.kotlinfirstdemo.base.BaseActivity
import com.example.kotlinfirstdemo.daemon.service.LocalService
import com.example.kotlinfirstdemo.daemon.service.RemoteService
import com.example.kotlinfirstdemo.daemon.service.SingleTestAlarmService
import com.example.kotlinfirstdemo.databinding.ActivityDaemonShowBinding
import com.example.kotlinfirstdemo.util.LogUtil

/**
 * 守护进程控制界面
 */
class DaemonShowActivity : BaseActivity<ActivityDaemonShowBinding>() {


    override fun initLayout(): Int {
        return R.layout.activity_daemon_show
    }

    override fun initView() {
        mBinding?.btnStartLocal?.setOnClickListener(object :View.OnClickListener {
            override fun onClick(v: View?) {
                startService(Intent(baseContext, LocalService::class.java))
                LogUtil.showE("点击了启动本地线程按钮")
            }
        })

        mBinding?.btnStartSingleAlarmService?.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                startService(Intent(baseContext,SingleTestAlarmService::class.java))
                LogUtil.showE("点击了启动SingleTestAlarmService")
            }
        })

        mBinding?.btnCloseLocal?.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View?) {
                stopService(Intent(baseContext, LocalService::class.java))
            }
        })

        mBinding?.btnCloseRemote?.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View?) {
                stopService(Intent(baseContext, RemoteService::class.java))
            }
        })

    }

    override fun initData() {

    }


}