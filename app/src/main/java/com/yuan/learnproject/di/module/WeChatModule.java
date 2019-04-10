package com.yuan.learnproject.di.module;

import com.yuan.learnproject.api.ApiService;
import com.yuan.learnproject.contract.WeChatContract;
import com.yuan.learnproject.di.scope.FragmentScope;
import com.yuan.learnproject.model.WeChatModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author yuan
 * @date 2019/4/9
 **/
@Module
public class WeChatModule {
    private WeChatContract.WeChatView mView;

    public WeChatModule(WeChatContract.WeChatView view) {
        this.mView = view;
    }

    @FragmentScope
    @Provides
    public WeChatContract.WeChatView provideView() {
        return mView;
    }

    @FragmentScope
    @Provides
    public WeChatContract.WeChatModel provideMainArticleModel(ApiService apiService) {
        return new WeChatModel(apiService);
    }
}
