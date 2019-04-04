package com.yuan.learnproject.di.component;

import com.yuan.learnproject.di.module.CollectModule;
import com.yuan.learnproject.di.scope.ActivityScope;
import com.yuan.learnproject.ui.fragment.CollectFragment;

import dagger.Component;

/**
 * @author yuan
 * @date 2019/4/3
 **/
@ActivityScope
@Component(modules = CollectModule.class, dependencies = AppComponent.class)
public interface CollectComponent {
    void inject(CollectFragment activity);
}
