package com.yuan.learnproject;

import android.app.Application;
import android.content.Context;

import com.yuan.learnproject.di.component.AppComponent;
import com.yuan.learnproject.di.component.DaggerAppComponent;
import com.yuan.learnproject.di.module.AppModule;

/**
 * @author yuan
 * @date 2019/1/27
 **/
public class MainApplication extends Application {
    private AppComponent mAppComponent;
    private static Context context;
    public static Context getContext(){
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        // 先 build 一下 project，会生成对应的 DaggerXXX 文件，再进行初始化
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }
}
