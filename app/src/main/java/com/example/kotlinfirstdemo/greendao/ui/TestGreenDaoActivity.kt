package com.example.kotlinfirstdemo.greendao.ui

import android.view.View
import com.example.kotlinfirstdemo.R
import com.example.kotlinfirstdemo.app.WorkApplication
import com.example.kotlinfirstdemo.base.BaseActivity
import com.example.kotlinfirstdemo.databinding.ActivityTestGreenDaoBinding
import com.example.kotlinfirstdemo.greendao.bean.MyUser
import com.example.kotlinfirstdemo.util.LogUtil
import com.greendao.gen.MyUserDao
import org.greenrobot.greendao.query.QueryBuilder
import org.greenrobot.greendao.query.WhereCondition

class TestGreenDaoActivity : BaseActivity<ActivityTestGreenDaoBinding>() {

    var mySession = WorkApplication.getApplication()?.getDaoSession()
    var mDao: MyUserDao? = null

    override fun initLayout(): Int {
        return R.layout.activity_test_green_dao
    }

    override fun initView() {
        mBinding?.tvAddSingle?.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View?) {
                addSingleUser()
            }
        })

        mBinding?.tvAddList?.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View?) {
                addListUser()
            }
        })

        mBinding?.tvQueryAll?.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                queryAll()
            }
        })

        mBinding?.tvDeleteUser?.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                deleteUser()
            }
        })

        //指定信息
        mBinding?.tv5?.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                updateSomeInfo()
            }
        })

        //批量更新
        mBinding?.tv6?.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                updateUsers()
            }
        })

        //getLimitUser
        //拿到前三条
        mBinding?.tv7?.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                logoUt(getLimitUser())
            }
        })

        //组合查询
        mBinding?.tv8?.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                logoUt(queryAndWhere())
            }
        })

        //查询总数量
        mBinding?.tv9?.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                queryCountSize()
            }
        })

        //删除所有
        mBinding?.tv11?.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                deleteAll()
            }
        })

    }

    override fun initData() {
        initGreenDao()
        addSingleUser()
        addSingleUser()
    }

    fun initGreenDao() {
        mDao = mySession?.myUserDao
    }

    private fun addSingleUser() {
        val user = MyUser()
        user.name = "张三"
        mDao?.insert(user)
    }

    private fun addListUser() {
        val list = ArrayList<MyUser>(100)
        for (index in 0..10) {
            val userTemp = MyUser()
            userTemp.name = "张三丰" + index
            userTemp.setIsCollect(index % 2 == 0)
            list.add(userTemp)
        }

        mDao?.insertInTx(list)
    }

    private fun deleteUser() {
        //删除指定id
        val deleteId = 2L
        mDao?.deleteByKey(deleteId)

        //删除指定数据
        mDao?.queryBuilder()
            ?.where(MyUserDao.Properties.Name.eq("张三丰3"))
            ?.buildDelete()
            ?.executeDeleteWithoutDetachingEntities()
    }

    //删除所有
    private fun deleteAll(){
        //删除所有数据
        mDao?.deleteAll()
    }

    private fun queryAll(): List<MyUser> {

        //查询所有数据
        val list: List<MyUser> = mDao?.queryBuilder()
            ?.orderAsc(MyUserDao.Properties.Name)
            ?.list() as List<MyUser>

        //查询指定名字的数据
        var list2: List<MyUser> = mDao?.queryBuilder()
            ?.where(MyUserDao.Properties.Name.eq("张三"))
            ?.orderAsc(MyUserDao.Properties.Name)
            ?.list() as List<MyUser>

        val list0 = list
//        var list0 = list2

        logoUt(list0)
        return list0
    }

    //查询总数量
    fun queryCountSize(): Int? {
        val size =  mDao?.queryBuilder()?.list()?.size
        LogUtil.showE(size.toString())
        return size
    }

    //更新指定信息
    fun updateSomeInfo() {
        val user: MyUser? = mDao?.queryBuilder()
            ?.where(MyUserDao.Properties.Name.eq("张三4"))
            ?.build()
            ?.unique()

            user?.name = "蜘蛛侠";
            mDao?.update(user);

    }

    //批量更新
    fun updateUsers() {
        val whereCondition: WhereCondition = MyUserDao.Properties.IsCollect.eq(true)

        val list = mDao?.queryBuilder()?.where(whereCondition)?.build()?.list()

        list?.forEach {
            if (it.isCollect()) {
                it.setIsCollect(false)
            }
        }

        mDao?.updateInTx(list)
    }

    //    查询所有返回数据 但只返回前三条数据 并且跳过第一条数据
    fun getLimitUser(): List<MyUser> {
        val list: List<MyUser> = mDao?.queryBuilder()
            ?.limit(3)
            ?.offset(1)//跳过第一条数据
            ?.list()
                as List<MyUser>;
        return list
    }

//    组合查询数据 查询姓名为"张三" 并且被选中的用户
    fun queryAndWhere():List<MyUser>{
        val qb :QueryBuilder<MyUser> = mDao?.queryBuilder() as QueryBuilder<MyUser>;
        qb.where(MyUserDao.Properties.Name.eq("张三"), MyUserDao.Properties.IsCollect.le(true));
        val list :List<MyUser> = qb.list();
        return list
    }

    fun logoUt(list:List<MyUser>){
        list?.forEach {
            LogUtil.showE(it.toString())
        }
    }

}