package com.yuan.learnproject.di.component;

import com.yuan.learnproject.di.module.MainArticleModule;
import com.yuan.learnproject.di.scope.FragmentScope;
import com.yuan.learnproject.ui.fragment.MainArticleFragment;

import dagger.Component;

@FragmentScope
@Component(modules = MainArticleModule.class, dependencies = AppComponent.class)
public interface MainArticleComponent {
    void inject(MainArticleFragment articleFragment);
}
