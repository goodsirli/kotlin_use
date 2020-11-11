package com.example.kotlinfirstdemo.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.example.kotlinfirstdemo.R
import com.example.kotlinfirstdemo.adapter.CustomViewListAdapter
import com.example.kotlinfirstdemo.constant.CustomViewConstant
import com.example.kotlinfirstdemo.bean.CustomViewBean

/**
 * 自定义view 列表
 */
class CustomViewListActivity: AppCompatActivity(),
        OnItemClickListener {

    var mRecyclerView:RecyclerView ?= null
    var mAdapter:CustomViewListAdapter ?= null
    var mList = ArrayList<CustomViewBean>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_recylerview)
        initRecyclerview()
    }

    init {
        val bean = CustomViewBean("环状百分比饼图",
            CustomViewConstant.TYPE1)
        mList.add(bean)
    }

    fun initRecyclerview(){
        mRecyclerView = findViewById(R.id.recyclerview_base)
        val layoutManager = LinearLayoutManager(this)
        mRecyclerView?.layoutManager = layoutManager


        mAdapter = CustomViewListAdapter(this,R.layout.firstfragment_tabitem,mList)
        mAdapter!!.setOnItemClickListener(this)

//        设置监听第二种方式
//        mAdapter!!.setOnItemClickListener(object:OnItemClickListener{
//            override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
//            }
//        })

        mRecyclerView?.adapter = mAdapter


    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
//        val intent = Intent(this,CustomViewShowActivity::class.java);
////        intent.setClass(this,CustomViewShowActivity::class.java)
//        startActivity(intent)

        val bean = adapter.data[position] as CustomViewBean

        val intent2 = Intent()
        intent2.setClass(this,
            CustomViewShowActivity::class.java)
        intent2.putExtra("customView",bean)
        startActivity(intent2)
    }
}