package com.yuan.learnproject.model;

import com.yuan.learnproject.api.ApiService;
import com.yuan.learnproject.bean.BaseBean;
import com.yuan.learnproject.bean.articles.MainArticleBean;
import com.yuan.learnproject.bean.articles.MainArticleDataBean;
import com.yuan.learnproject.bean.articles.MainBannerBean;
import com.yuan.learnproject.contract.MainArticleContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author yuan
 * @date 2019/3/1
 **/
public class MainArticleModel extends CommonCollectModel implements MainArticleContract.MainArticleModel {
    private ApiService mApiService;

    public MainArticleModel(ApiService mApiService) {
        super(mApiService);
        this.mApiService = mApiService;
    }

    @Override
    public Observable<BaseBean<MainArticleBean>> getMainArticle(int page) {
        return mApiService.getMainArticles(page);
    }

    @Override
    public Observable<BaseBean<List<MainArticleDataBean>>> getTopArticles() {
        return mApiService.getTopArticles();
    }

    @Override
    public Observable<BaseBean<List<MainBannerBean>>> getBanner() {
        return mApiService.getBanner();
    }
}
