package com.yuan.learnproject.model;

import com.yuan.learnproject.api.ApiService;
import com.yuan.learnproject.bean.BaseBean;
import com.yuan.learnproject.bean.project.ProjectTreeBean;
import com.yuan.learnproject.contract.ProjectTreeContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author yuan
 * @date 2019/3/22
 **/
public class ProjectTreeModel implements ProjectTreeContract.ProjectTreeModel {
    private ApiService mApiService;

    public ProjectTreeModel(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<List<ProjectTreeBean>>> getProjectTree() {
        return mApiService.getProjectTree();
    }
}
