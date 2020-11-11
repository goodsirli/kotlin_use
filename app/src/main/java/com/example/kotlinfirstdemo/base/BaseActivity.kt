package com.example.kotlinfirstdemo.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding
import com.example.kotlinfirstdemo.R

abstract class BaseActivity< T : ViewDataBinding> : AppCompatActivity() {

    protected val TAG: String = this::class.simpleName!!
    var mIsShowTitle: Boolean? = true
    var mIsShowStatusBar: Boolean? = true
    var mBinding : T ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.inflate(layoutInflater,initLayout(),null,false)
        setContentView(mBinding?.root)

        initView()
        initData()

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

    override fun onDestroy() {
        super.onDestroy()
    }

}


