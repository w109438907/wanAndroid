package com.yuan.learnproject.model;

import com.yuan.learnproject.api.ApiService;
import com.yuan.learnproject.bean.BaseBean;
import com.yuan.learnproject.bean.articles.CollectionArticleBean;
import com.yuan.learnproject.contract.CollectContract;

import io.reactivex.Observable;

/**
 * @author yuan
 * @date 2019/4/3
 **/
public class CollectModel implements CollectContract.CollectModel {
    protected ApiService mApiService;

    public CollectModel(ApiService apiService) {
        mApiService = apiService;
    }
    @Override
    public Observable<BaseBean<CollectionArticleBean>> getCollectArticles(int pageIndex) {
        return mApiService.getCollectedArticles(pageIndex);
    }

    @Override
    public Observable<BaseBean<Object>> deleteCollectArticle(int articleId, int originId) {
        return mApiService.deleteCollectArticle(articleId, originId);
    }
}
