package com.yuan.learnproject.di.module;

import com.yuan.learnproject.api.ApiService;
import com.yuan.learnproject.contract.ProjectTreeContract;
import com.yuan.learnproject.di.scope.FragmentScope;
import com.yuan.learnproject.model.ProjectTreeModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author yuan
 * @date 2019/3/22
 **/
@Module
public class ProjectTreeModule {
    private ProjectTreeContract.ProjectTreeView mView;

    public ProjectTreeModule(ProjectTreeContract.ProjectTreeView view) {
        this.mView = view;
    }

    @FragmentScope
    @Provides
    public ProjectTreeContract.ProjectTreeView provideView() {
        return mView;
    }

    @FragmentScope
    @Provides
    public ProjectTreeContract.ProjectTreeModel provideProjectTreeModel(ApiService apiService) {
        return new ProjectTreeModel(apiService);
    }
}
