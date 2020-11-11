package com.example.kotlinfirstdemo.recyclertest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinfirstdemo.R
import com.example.kotlinfirstdemo.adapter.FirstFragmentAdapter
import com.example.kotlinfirstdemo.bean.FirstFragmentBean
import com.example.kotlinfirstdemo.util.DFSTest
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FragmentRecyclerTest : Fragment() {

    private val mList=ArrayList<FirstFragmentBean>()
    private var mAdapter:FirstFragmentAdapter?= null
    private var mLastPosition:Int = 0
    private var mPageNum = 1
    private var mPageContent:Int = 0
    private var mPageSize = 10

    private var mRecyclerView : RecyclerView? =null;

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recyclertest, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerView = view.findViewById(R.id.recyclerViewFirst)
        initRecyclerView()

        DFSTest.testDFS()

    }

    fun initRecyclerView(){

        val bean:FirstFragmentBean= FirstFragmentBean();
        bean.name = "笑话"
        bean.age = 17
        bean.weight = 100

        mList.add(bean)

        for(a in 0..20){
            val bean:FirstFragmentBean = FirstFragmentBean();
            bean.weight = 110 + a
            bean.age = 30 +a
            bean.name = "张三" + a

            mList.add(bean)
        }

        mAdapter = FirstFragmentAdapter(mList)


        mAdapter!!.setOnItemClickListener(object : FirstFragmentAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                mAdapter!!.cleckAll(position)
            }
        })

        mRecyclerView?.layoutManager = LinearLayoutManager(context)
        mRecyclerView?.adapter = mAdapter
        mRecyclerView?.isNestedScrollingEnabled = false
        mRecyclerView?.isFocusable = false
        mRecyclerView?.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            internal var isSlidingToLast =  false
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                var manager = recyclerView!!.layoutManager as LinearLayoutManager
                //当不滚动时
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    //获取最后一个完全显示的itemposition
                    mLastPosition = manager.findLastCompletelyVisibleItemPosition();
                    val totalCount = manager.itemCount
                    if(mLastPosition == totalCount - 1 && isSlidingToLast){
                        mPageNum++
                        if(mPageNum > mPageContent){
                            Toast.makeText(context,"已经到底了",Toast.LENGTH_SHORT).show()
                            return
                        }
                    }
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                //dx 用来判断横向滑动方向，dy 用来判断纵向滑动方向
                //大于0 表示正在向右滚动，小于或者等于0 表示停止或向左滚动
                isSlidingToLast = dy > 0
            }
        });

    }

}