package com.yuan.learnproject.di.component;

import com.yuan.learnproject.di.module.RegisterModule;
import com.yuan.learnproject.di.scope.ActivityScope;
import com.yuan.learnproject.ui.activity.RegisterActivity;

import dagger.Component;

/**
 * @author yuan
 * @date 2019/3/3
 **/
@ActivityScope
@Component(modules = RegisterModule.class, dependencies = AppComponent.class)
public interface RegisterComponent {
    void inject(RegisterActivity activity);
}
