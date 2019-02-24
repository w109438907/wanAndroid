package com.yuan.learnproject.di.module;

import com.yuan.learnproject.contract.MainContract;
import com.yuan.learnproject.di.scope.ActivityScope;
import com.yuan.learnproject.model.MainModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author yuan
 * @date 2019/1/27
 **/
@Module
public class MainActivityModule {
    private MainContract.MainView mView;

    public MainActivityModule(MainContract.MainView view) {
        this.mView = view;
    }

    @ActivityScope
    @Provides
    public MainContract.MainView provideView() {
        return mView;
    }

    @ActivityScope
    @Provides
    public MainContract.MainModel provideModel() {
        return new MainModel();
    }
}
