package com.yuan.learnproject.di.component;

import com.yuan.learnproject.di.module.NavigationModule;
import com.yuan.learnproject.di.scope.FragmentScope;
import com.yuan.learnproject.ui.fragment.NavigationFragment;

import dagger.Component;

/**
 * @author yuan
 * @date 2019/3/17
 **/
@FragmentScope
@Component(modules = NavigationModule.class, dependencies = AppComponent.class)
public interface NavigationComponent {
    void inject(NavigationFragment articleFragment);
}

