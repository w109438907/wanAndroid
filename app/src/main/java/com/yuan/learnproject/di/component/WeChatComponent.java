package com.yuan.learnproject.di.component;

import com.yuan.learnproject.di.module.WeChatModule;
import com.yuan.learnproject.di.scope.FragmentScope;
import com.yuan.learnproject.ui.fragment.WeChatFragment;

import dagger.Component;

/**
 * @author yuan
 * @date 2019/4/9
 **/
@FragmentScope
@Component(modules = WeChatModule.class, dependencies = AppComponent.class)
public interface WeChatComponent {
    void inject(WeChatFragment fragment);
}
