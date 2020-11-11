package com.example.kotlinfirstdemo.adapter

import android.content.Context
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.kotlinfirstdemo.R
import com.example.kotlinfirstdemo.bean.CustomViewBean

class CustomViewListAdapter(layoutResId: Int, data: MutableList<CustomViewBean>?) :
    BaseQuickAdapter<CustomViewBean, BaseViewHolder>(layoutResId, data) {

    var mContext:Context ?= null

    constructor(context: Context,layoutId:Int,data :MutableList<CustomViewBean>): this(layoutId,data){
        mContext = context
    }

    override fun convert(holder: BaseViewHolder, item: CustomViewBean) {
        holder.setText(R.id.tvTabItem,item.name)
    }

}