package com.yuan.learnproject.di.module;

import com.yuan.learnproject.api.ApiService;
import com.yuan.learnproject.contract.KnowledgeContract;
import com.yuan.learnproject.di.scope.FragmentScope;
import com.yuan.learnproject.model.KnowledgeModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author yuan
 * @date 2019/3/16
 **/
@Module
public class KnowledgeModule {
    private KnowledgeContract.KnowledgeView mView;

    public KnowledgeModule(KnowledgeContract.KnowledgeView view) {
        this.mView = view;
    }

    @FragmentScope
    @Provides
    public KnowledgeContract.KnowledgeView provideView() {
        return mView;
    }

    @FragmentScope
    @Provides
    public KnowledgeContract.KnowledgeModel provideMainArticleModel(ApiService apiService) {
        return new KnowledgeModel(apiService);
    }
}
