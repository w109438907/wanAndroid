package com.yuan.learnproject.di.component;

import android.app.Application;

import com.yuan.learnproject.di.module.AppModule;
import com.yuan.learnproject.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author yuan
 * @date 2019/1/27
 **/
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    Application getApplication();
}
