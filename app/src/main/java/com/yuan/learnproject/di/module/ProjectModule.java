package com.yuan.learnproject.di.module;

import com.yuan.learnproject.api.ApiService;
import com.yuan.learnproject.contract.ProjectContract;
import com.yuan.learnproject.di.scope.FragmentScope;
import com.yuan.learnproject.model.ProjectModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author yuan
 * @date 2019/3/22
 **/
@Module
public class ProjectModule {
    private ProjectContract.ProjectView mView;

    public ProjectModule(ProjectContract.ProjectView view) {
        this.mView = view;
    }

    @FragmentScope
    @Provides
    public ProjectContract.ProjectView provideView() {
        return mView;
    }

    @FragmentScope
    @Provides
    public ProjectContract.ProjectModel provideProjectModel(ApiService apiService) {
        return new ProjectModel(apiService);
    }
}
