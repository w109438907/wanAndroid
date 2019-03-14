package com.yuan.learnproject.di.component;

import com.yuan.learnproject.di.module.KnowledgeTreeModule;
import com.yuan.learnproject.di.scope.FragmentScope;
import com.yuan.learnproject.ui.fragment.KnowledgeTreeFragment;

import dagger.Component;

/**
 * @author yuan
 * @date 2019/3/8
 **/
@FragmentScope
@Component(modules = KnowledgeTreeModule.class, dependencies = AppComponent.class)
public interface KnowledgeTreeComponent {
    void inject(KnowledgeTreeFragment fragment);
}
