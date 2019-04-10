package com.yuan.learnproject.model;

import com.yuan.learnproject.api.ApiService;
import com.yuan.learnproject.bean.BaseBean;
import com.yuan.learnproject.bean.articles.MainArticleBean;
import com.yuan.learnproject.contract.WeChatContract;

import io.reactivex.Observable;

/**
 * @author yuan
 * @date 2019/4/9
 **/
public class WeChatModel extends CommonCollectModel implements WeChatContract.WeChatModel {

    public WeChatModel(ApiService apiService) {
        super(apiService);
    }

    @Override
    public Observable<BaseBean<MainArticleBean>> getWeChatArticles(int page, int id) {
        return mApiService.getWeChatArticles(page, id);
    }
}
