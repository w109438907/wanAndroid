package com.yuan.learnproject.model;

import com.yuan.learnproject.api.ApiService;
import com.yuan.learnproject.bean.BaseBean;
import com.yuan.learnproject.bean.LoginResponseBean;
import com.yuan.learnproject.contract.LoginContract;

import io.reactivex.Observable;

/**
 * @author yuan
 * @date 2019/3/3
 **/
public class LoginModel implements LoginContract.LoginModel {

    private ApiService mApiService;

    public LoginModel(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<LoginResponseBean>> doLogin(String username, String password) {
        return mApiService.login(username, password);
    }
}
