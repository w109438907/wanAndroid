package com.yuan.learnproject.model;

import com.yuan.learnproject.api.ApiService;
import com.yuan.learnproject.bean.BaseBean;
import com.yuan.learnproject.bean.NavigationBean;
import com.yuan.learnproject.contract.NavigationContract;

import io.reactivex.Observable;

/**
 * @author yuan
 * @date 2019/3/17
 **/
public class NavigationModel implements NavigationContract.NavigationModel {
    protected ApiService mApiService;

    public NavigationModel(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<NavigationBean>> getNavigation() {
        return mApiService.getNavigation();
    }
}
