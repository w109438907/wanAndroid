package com.yuan.learnproject.di.module;

import com.yuan.learnproject.api.ApiService;
import com.yuan.learnproject.contract.KnowledgeTreeContract;
import com.yuan.learnproject.di.scope.FragmentScope;
import com.yuan.learnproject.model.KnowledgeTreeModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author yuan
 * @date 2019/3/8
 **/
@Module
public class KnowledgeTreeModule {
    private KnowledgeTreeContract.KnowledgeView mView;

    public KnowledgeTreeModule(KnowledgeTreeContract.KnowledgeView view) {
        this.mView = view;
    }

    @FragmentScope
    @Provides
    public KnowledgeTreeContract.KnowledgeView provideView() {
        return mView;
    }

    @FragmentScope
    @Provides
    public KnowledgeTreeContract.KnowledgeModel provideMainArticleModel(ApiService apiService) {
        return new KnowledgeTreeModel(apiService);
    }
}
