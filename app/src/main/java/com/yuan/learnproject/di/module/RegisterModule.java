package com.yuan.learnproject.di.module;

import com.yuan.learnproject.api.ApiService;
import com.yuan.learnproject.contract.RegisterContract;
import com.yuan.learnproject.di.scope.ActivityScope;
import com.yuan.learnproject.model.RegisterModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author yuan
 * @date 2019/3/3
 **/
@Module
public class RegisterModule {
    private RegisterContract.RegisterView mView;

    public RegisterModule(RegisterContract.RegisterView view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    public RegisterContract.RegisterView provideView() {
        return mView;
    }

    @ActivityScope
    @Provides
    public RegisterContract.RegisterModel provideRegisterModel(ApiService apiService) {
        return new RegisterModel(apiService);
    }
}
