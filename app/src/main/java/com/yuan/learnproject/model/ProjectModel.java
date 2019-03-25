package com.yuan.learnproject.model;

import com.yuan.learnproject.api.ApiService;
import com.yuan.learnproject.bean.BaseBean;
import com.yuan.learnproject.bean.articles.MainArticleBean;
import com.yuan.learnproject.contract.ProjectContract;

import io.reactivex.Observable;

/**
 * @author yuan
 * @date 2019/3/22
 **/
public class ProjectModel extends CommonCollectModel implements ProjectContract.ProjectModel {

    public ProjectModel(ApiService apiService) {
        super(apiService);
    }

    @Override
    public Observable<BaseBean<MainArticleBean>> getProject(int page, int cid) {
        return mApiService.getProjectList(page, cid);
    }
}
