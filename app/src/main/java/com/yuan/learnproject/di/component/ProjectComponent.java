package com.yuan.learnproject.di.component;

import com.yuan.learnproject.di.module.ProjectModule;
import com.yuan.learnproject.di.scope.FragmentScope;
import com.yuan.learnproject.ui.fragment.ProjectFragment;

import dagger.Component;

/**
 * @author yuan
 * @date 2019/3/22
 **/
@FragmentScope
@Component(modules = ProjectModule.class, dependencies = AppComponent.class)
public interface ProjectComponent {
    void inject(ProjectFragment fragment);
}
