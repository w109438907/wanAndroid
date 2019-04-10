package com.yuan.learnproject.di.module;

import com.yuan.learnproject.api.ApiService;
import com.yuan.learnproject.contract.WeChatTreeContract;
import com.yuan.learnproject.di.scope.FragmentScope;
import com.yuan.learnproject.model.WeChatTreeModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author yuan
 * @date 2019/4/9
 **/
@Module
public class WeChatTreeModule {
    private WeChatTreeContract.WeChatTreeView mView;

    public WeChatTreeModule(WeChatTreeContract.WeChatTreeView view) {
        this.mView = view;
    }

    @FragmentScope
    @Provides
    public WeChatTreeContract.WeChatTreeView provideView() {
        return mView;
    }

    @FragmentScope
    @Provides
    public WeChatTreeContract.WeChatTreeModel provideMainArticleModel(ApiService apiService) {
        return new WeChatTreeModel(apiService);
    }
}
