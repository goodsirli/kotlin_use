package com.example.kotlinfirstdemo.dagger2;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kotlinfirstdemo.R;
import com.example.kotlinfirstdemo.app.WorkApplication;
import com.example.kotlinfirstdemo.util.LogUtil;
import com.example.kotlinfirstdemo.util.SpUtil;
import com.example.kotlinfirstdemo.util.ToastUtil;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

public class DaggerTestActivity extends AppCompatActivity {

    @Inject
    @Named("cached")
    OkHttpClient mCachedClient;

    @Inject
    @Named("non_cached")
    OkHttpClient mNoCachedClient;

    @Inject
    SharedPreferences mSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger_test2);

        ((WorkApplication) getApplication()).getAppComponent().inject(this);

//        testSp();
        testClient();

    }

    private void testSp(){

        mSP.edit().putString("name","lili").commit();

        SpUtil.Companion.putString(this, "name", "lili");

        String name = mSP.getString( "name","张三");
//        String name = SpUtil.Companion.getString(this, "name");
        LogUtil.Companion.showE(name);
    }

    private void testClient(){

        if(mCachedClient == null){
            LogUtil.Companion.showE("mCachedClient 为空");
        }
        else{
            LogUtil.Companion.showE("mCachedClient 不为空");
            ToastUtil.Companion.showLong("mCachedClient 不为空");
        }

        if(mNoCachedClient == null){
            LogUtil.Companion.showE("mNoCachedClient 为空");
        }
        else{
            LogUtil.Companion.showE("mNoCachedClient 不为空");
        }
        Cache cache = mCachedClient.cache();
        if(cache == null){
            LogUtil.Companion.showE("cachedclient 没有cache");
        }else{
            LogUtil.Companion.showE("cachedclient 有cache");
        }

        Cache noCache = mNoCachedClient.cache();
        if(noCache == null){
            LogUtil.Companion.showE("mNoCachedClient 没有cache");
        }else{
            LogUtil.Companion.showE("mNoCachedClient 有cache");
        }
    }
}