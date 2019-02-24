package com.yuan.learnproject.di.module;

import android.app.Application;

import dagger.Module;

/**
 * @author yuan
 * @date 2019/1/27
 **/
@Module
public class AppModule {
    private Application mApplication;
    public AppModule(Application application) {
        mApplication = application;
    }
}
