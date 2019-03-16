package com.yuan.learnproject.di.module;

import com.yuan.learnproject.api.ApiService;
import com.yuan.learnproject.contract.KnowledgeTreeContract;
import com.yuan.learnproject.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * @author yuan
 * @date 2019/3/8
 **/
@Module
public class KnowledgeTreeModule {
    private KnowledgeTreeContract.KnowledgeTreeView mView;

    public KnowledgeTreeModule(KnowledgeTreeContract.KnowledgeTreeView view) {
        this.mView = view;
    }

    @FragmentScope
    @Provides
    public KnowledgeTreeContract.KnowledgeTreeView provideView() {
        return mView;
    }

    @FragmentScope
    @Provides
    public KnowledgeTreeContract.KnowledgeTreeModel provideMainArticleModel(ApiService apiService) {
        return new com.yuan.learnproject.model.KnowledgeTreeModel(apiService);
    }
}
