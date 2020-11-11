package com.example.kotlinfirstdemo.dagger2;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules={AppModule.class, NetModule.class})
public interface AppComponent {
    void inject(DaggerTestActivity activity);
    void inject(Application application);
//    void inject(Fragment fragment);
}
