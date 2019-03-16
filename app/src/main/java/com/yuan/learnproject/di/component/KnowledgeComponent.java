package com.yuan.learnproject.di.component;

import com.yuan.learnproject.di.module.KnowledgeModule;
import com.yuan.learnproject.di.scope.FragmentScope;
import com.yuan.learnproject.ui.fragment.KnowledgeFragment;

import dagger.Component;

/**
 * @author yuan
 * @date 2019/3/16
 **/
@FragmentScope
@Component(modules = KnowledgeModule.class, dependencies = AppComponent.class)
public interface KnowledgeComponent {
    void inject(KnowledgeFragment fragment);
}
