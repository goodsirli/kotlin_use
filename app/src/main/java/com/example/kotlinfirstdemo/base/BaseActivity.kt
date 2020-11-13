package com.example.kotlinfirstdemo.base

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.kotlinfirstdemo.activitymanager.ActivityWorkManager
import com.example.kotlinfirstdemo.rx.RxBus
import com.example.kotlinfirstdemo.util.LogUtil
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

abstract class BaseActivity< T : ViewDataBinding> : AppCompatActivity(),
    EasyPermissions.PermissionCallbacks,
EasyPermissions.RationaleCallbacks{

    private var mRxBus: RxBus?= null
    var mIsShowTitle: Boolean? = true
    var mIsShowStatusBar: Boolean? = true
    var mBinding : T ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.inflate(layoutInflater,initLayout(),null,false)
        setContentView(mBinding?.root)

        ActivityWorkManager.getInstance().addActivity(this)
        mRxBus = RxBus.getInstance();

        initView()
        initData()
    }


    override fun onDestroy() {
        super.onDestroy()
        ActivityWorkManager.getInstance().removeActivity(this)
    }

    abstract fun initLayout(): Int

    abstract fun initView()

    abstract fun initData()

    fun showTitleBar():Boolean{
        return true
    }

    fun setShowTitle(isShowTitle:Boolean){
        mIsShowTitle = isShowTitle
    }

    fun setShowStatusBar(isShowStatusBar:Boolean){
        mIsShowStatusBar = isShowStatusBar
    }

    fun showSoftInput(){
        val inputManager :InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        if (currentFocus != null && null != inputManager){
            inputManager.showInputMethodAndSubtypeEnabler(null )
        }
    }

    fun hideSoftInput(){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (currentFocus != null && null != imm) {
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

    }

    //权限请求dialog,拒绝回调
    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog .Builder(this).build().show();
        }
    }

    //权限请求dialog,接受回调
    override fun onRationaleAccepted(requestCode: Int) {

    }

    override fun onRationaleDenied(requestCode: Int) {

    }

    //注册RxBus
    protected fun <T> registerRxBus(eventType:Class<T> , action: Consumer<T>) {
        val disposable:Disposable? = mRxBus?.doSubscribe(eventType,action,Consumer<Throwable>{
            LogUtil.showE(it.toString())
        })

        mRxBus?.addSubscription(this,disposable);
    }

    //注销RxBus
    protected fun unBindRxBus(){
        mRxBus?.unSubscribe(this)
    }
}


