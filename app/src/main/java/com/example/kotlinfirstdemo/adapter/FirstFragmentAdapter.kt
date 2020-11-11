package com.example.kotlinfirstdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinfirstdemo.R
import com.example.kotlinfirstdemo.bean.FirstFragmentBean

class FirstFragmentAdapter constructor(var mList:List<FirstFragmentBean>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mOnItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.firstfragment_tabitem, null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size;
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var entity = mList[position]

        try {
            holder as  ViewHolder
            holder.tabItemName.text = entity.name

        } catch (e: Exception) {
            e.printStackTrace()
        }

        //判断是否设置了监听器
        if (mOnItemClickListener != null) {
            //为ItemView设置监听器-
            holder.itemView.setOnClickListener {
                val position = holder.layoutPosition // 1
                mOnItemClickListener!!.onItemClick(holder.itemView, position) // 2
            }

        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tabItemName = itemView.findViewById(R.id.tvTabItem) as TextView
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = onItemClickListener
    }

    //  删除 打勾 全选
    fun cleckAll(is_checked: Int) { //全选 删除多少那里要删除全部
        for (a in mList.indices) {
            if (is_checked == a) {
                mList.get(a).name = "选中了"
            } else {

            }
        }
        notifyDataSetChanged()
    }

}