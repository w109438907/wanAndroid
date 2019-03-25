package com.yuan.learnproject.di.component;

import com.yuan.learnproject.di.scope.FragmentScope;
import com.yuan.learnproject.ui.fragment.ProjectTreeFragment;
import com.yuan.learnproject.di.module.ProjectTreeModule;
import dagger.Component;

/**
 * @author yuan
 * @date 2019/3/22
 **/
@FragmentScope
@Component(modules = ProjectTreeModule.class, dependencies = AppComponent.class)
public interface ProjectTreeComponent {
    void inject(ProjectTreeFragment fragment);
}

