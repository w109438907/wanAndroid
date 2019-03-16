package com.yuan.learnproject.model;

import com.yuan.learnproject.api.ApiService;
import com.yuan.learnproject.bean.BaseBean;
import com.yuan.learnproject.bean.articles.MainArticleBean;
import com.yuan.learnproject.contract.KnowledgeContract;

import io.reactivex.Observable;

/**
 * @author yuan
 * @date 2019/3/16
 **/
public class KnowledgeModel extends CommonCollectModel implements KnowledgeContract.KnowledgeModel {

    public KnowledgeModel(ApiService apiService) {
        super(apiService);
    }


    @Override
    public Observable<BaseBean<MainArticleBean>> getKnowledgeList(int page, int cid) {
        return mApiService.getKnowledgeList(page, cid);
    }
}