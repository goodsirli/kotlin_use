package com.example.kotlinfirstdemo.dagger2;

import android.app.Application;

import com.example.kotlinfirstdemo.app.WorkApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }
}
