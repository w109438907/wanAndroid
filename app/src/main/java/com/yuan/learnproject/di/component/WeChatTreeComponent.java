package com.yuan.learnproject.di.component;

import com.yuan.learnproject.di.module.WeChatTreeModule;
import com.yuan.learnproject.di.scope.FragmentScope;
import com.yuan.learnproject.ui.fragment.WeChatTreeFragment;

import dagger.Component;

/**
 * @author yuan
 * @date 2019/4/9
 **/
@FragmentScope
@Component(modules = WeChatTreeModule.class, dependencies = AppComponent.class)
public interface WeChatTreeComponent {
    void inject(WeChatTreeFragment fragment);
}