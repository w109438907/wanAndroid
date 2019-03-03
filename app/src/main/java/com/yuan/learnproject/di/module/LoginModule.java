package com.yuan.learnproject.di.module;

import com.yuan.learnproject.api.ApiService;
import com.yuan.learnproject.contract.LoginContract;
import com.yuan.learnproject.di.scope.ActivityScope;
import com.yuan.learnproject.model.LoginModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author yuan
 * @date 2019/3/3
 **/
@Module
public class LoginModule {
    private LoginContract.LoginView mView;

    public LoginModule(LoginContract.LoginView view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    public LoginContract.LoginView provideView() {
        return mView;
    }

    @ActivityScope
    @Provides
    public LoginContract.LoginModel provideLoginModel(ApiService apiService) {
        return new LoginModel(apiService);
    }
}
