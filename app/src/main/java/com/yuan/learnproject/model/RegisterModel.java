package com.yuan.learnproject.model;

import com.yuan.learnproject.api.ApiService;
import com.yuan.learnproject.bean.BaseBean;
import com.yuan.learnproject.bean.LoginResponseBean;
import com.yuan.learnproject.contract.RegisterContract;

import io.reactivex.Observable;

/**
 * @author yuan
 * @date 2019/3/3
 **/
public class RegisterModel implements RegisterContract.RegisterModel {

    private ApiService mApiService;

    public RegisterModel(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<LoginResponseBean>> register(String username, String password, String rePassword) {
        return mApiService.register(username, password, rePassword);
    }
}
