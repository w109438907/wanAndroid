package com.yuan.learnproject.model;

import com.yuan.learnproject.api.ApiService;
import com.yuan.learnproject.bean.BaseBean;
import com.yuan.learnproject.contract.CommonCollectContract;

import io.reactivex.Observable;

/**
 * @author yuan
 * @date 2019/3/3
 **/
public class CommonCollectModel implements CommonCollectContract.CommonCollectModel {
    protected ApiService mApiService;

    public CommonCollectModel(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<Object>> collectArticle(int articleId) {
        return mApiService.collectArticle(articleId);
    }

    @Override
    public Observable<BaseBean<Object>> cancelCollectArticle(int articleId) {
        return mApiService.cancelCollectArticle(articleId);
    }
}
