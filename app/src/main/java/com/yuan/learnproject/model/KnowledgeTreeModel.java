package com.yuan.learnproject.model;

import com.yuan.learnproject.api.ApiService;
import com.yuan.learnproject.bean.BaseBean;
import com.yuan.learnproject.bean.knowledge.TreesBean;
import com.yuan.learnproject.contract.KnowledgeTreeContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author yuan
 * @date 2019/3/8
 **/
public class KnowledgeTreeModel implements KnowledgeTreeContract.KnowledgeTreeModel {
    private ApiService mApiService;

    public KnowledgeTreeModel(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<List<TreesBean>>> getKnowledgeTree() {
        return mApiService.getKnowledgeTree();
    }
}