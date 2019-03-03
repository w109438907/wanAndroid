package com.yuan.learnproject.di.module;

import com.yuan.learnproject.api.ApiService;
import com.yuan.learnproject.contract.MainArticleContract;
import com.yuan.learnproject.di.scope.ActivityScope;
import com.yuan.learnproject.di.scope.FragmentScope;
import com.yuan.learnproject.model.MainArticleModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author yuan
 * @date 2019/3/1
 **/
@Module
public class MainArticleModule {
    private MainArticleContract.MainArticleView mView;

    public MainArticleModule(MainArticleContract.MainArticleView view) {
        this.mView = view;
    }

    @FragmentScope
    @Provides
    public MainArticleContract.MainArticleView provideView() {
        return mView;
    }

    @FragmentScope
    @Provides
    public MainArticleContract.MainArticleModel provideMainArticleModel(ApiService apiService) {
        return new MainArticleModel(apiService);
    }
}
