package com.yuan.learnproject.di.component;

import com.yuan.learnproject.di.module.LoginModule;
import com.yuan.learnproject.di.scope.ActivityScope;
import com.yuan.learnproject.ui.activity.LoginActivity;

import dagger.Component;

/**
 * @author yuan
 * @date 2019/3/3
 **/
@ActivityScope
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(LoginActivity activity);
}
