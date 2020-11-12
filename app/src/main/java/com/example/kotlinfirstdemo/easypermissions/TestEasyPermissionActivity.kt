package com.example.kotlinfirstdemo.easypermissions

import android.Manifest
import android.content.Intent
import com.example.kotlinfirstdemo.R
import com.example.kotlinfirstdemo.base.BaseActivity
import com.example.kotlinfirstdemo.databinding.ActivityTestEasyPermissionBinding
import com.example.kotlinfirstdemo.activitymanager.ActivityWorkManager
import com.example.kotlinfirstdemo.util.LogUtil
import com.example.kotlinfirstdemo.util.ToastUtil
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

class TestEasyPermissionActivity : BaseActivity<ActivityTestEasyPermissionBinding>() {

    companion object{
        private  val REQUEST_PERMISSION_CODE :Int = 1234
        //要请求的权限
        private val mPermissions =  Manifest.permission.CALL_PHONE

        //    private val mPermissions =  arrayOf(
//        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//        Manifest.permission.READ_EXTERNAL_STORAGE,
//        Manifest.permission.CALL_PHONE,
//        Manifest.permission.READ_PHONE_STATE,
//        Manifest.permission.ACCESS_NETWORK_STATE
//    )
    }

    override fun initLayout(): Int {
        return R.layout.activity_test_easy_permission
    }

    override fun initView() {
        if(EasyPermissions.hasPermissions(this, mPermissions)){
            ToastUtil.showLong("有权限,进入主页面")
        }else{
            EasyPermissions.requestPermissions(this,
                "请求相关权限，拒绝之后则无法使用app",
                REQUEST_PERMISSION_CODE,
                mPermissions
            )
        }
    }

    override fun initData() {

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this)
    }

    //拒绝请求
    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        super.onPermissionsDenied(requestCode, perms)
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {//拒绝权限且点击了不再提示
            AppSettingsDialog.Builder(this).build().show();//跳转应用设置页面
        } else {
            EasyPermissions.requestPermissions(this,
                "请求必要的权限,拒绝权限可能会无法使用app",
                REQUEST_PERMISSION_CODE,
                mPermissions);
        }
    }

    //接受请求
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        super.onPermissionsGranted(requestCode, perms)
        ToastUtil.showLong("有权限,进入主页面")
    }

    //请求权限dialog，接受（确定）回调
    override fun onRationaleAccepted(requestCode: Int) {
        super.onRationaleAccepted(requestCode)
        LogUtil.showE("确定")
    }

    //请求权限dialog，拒绝（取消）回调，在这里做退出页面或者退出appd的操作。
     override fun onRationaleDenied(requestCode: Int) {
        super.onRationaleDenied(requestCode)
        LogUtil.showE("取消")

//        finish()
        ActivityWorkManager.getInstance().appExit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            //当从软件设置界面，返回当前程序时候
            AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE->
            if (EasyPermissions.hasPermissions(this, mPermissions)) {
                ToastUtil.showLong("有权限,dosomthing")
                LogUtil.showE("有权限")
            } else {
                LogUtil.showE("没权限")
            }
        }
    }

}