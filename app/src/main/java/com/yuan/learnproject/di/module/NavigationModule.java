package com.yuan.learnproject.di.module;

import com.yuan.learnproject.api.ApiService;
import com.yuan.learnproject.contract.NavigationContract;
import com.yuan.learnproject.di.scope.FragmentScope;
import com.yuan.learnproject.model.NavigationModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author yuan
 * @date 2019/3/17
 **/
@Module
public class NavigationModule {
    private NavigationContract.NavigationView mView;

    public NavigationModule(NavigationContract.NavigationView view) {
        this.mView = view;
    }

    @FragmentScope
    @Provides
    public NavigationContract.NavigationView provideView() {
        return mView;
    }

    @FragmentScope
    @Provides
    public NavigationContract.NavigationModel provideNavigationModel(ApiService apiService) {
        return new NavigationModel(apiService);
    }
}
