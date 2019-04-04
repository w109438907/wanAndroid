package com.yuan.learnproject.di.module;

import com.yuan.learnproject.api.ApiService;
import com.yuan.learnproject.contract.CollectContract;
import com.yuan.learnproject.di.scope.ActivityScope;
import com.yuan.learnproject.model.CollectModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author yuan
 * @date 2019/4/3
 **/
@Module
public class CollectModule {
    private CollectContract.CollectView mView;

    public CollectModule(CollectContract.CollectView view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    public CollectContract.CollectView provideView() {
        return mView;
    }

    @ActivityScope
    @Provides
    public CollectContract.CollectModel provideCollectModel(ApiService apiService) {
        return new CollectModel(apiService);
    }
}
