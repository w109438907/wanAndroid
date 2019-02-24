package com.yuan.learnproject.di.component;

import com.yuan.learnproject.di.module.MainActivityModule;
import com.yuan.learnproject.di.scope.ActivityScope;
import com.yuan.learnproject.ui.activity.MainActivity;

import dagger.Component;

/**
 * @author yuan
 * @date 2019/1/27
 **/
@ActivityScope
@Component(modules = MainActivityModule.class, dependencies = AppComponent.class)
public interface MainActivityComponent {
    void inject(MainActivity activity);
}
