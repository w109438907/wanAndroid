package com.yuan.learnproject.presenter;

import com.yuan.learnproject.bean.articles.MainArticleBean;
import com.yuan.learnproject.contract.ProjectContract;
import com.yuan.learnproject.rx.CommonSubscriber;
import com.yuan.learnproject.rx.RxHttpResponseTransformer;

import javax.inject.Inject;

/**
 * @author yuan
 * @date 2019/3/22
 **/
public class ProjectPresenter extends CommonCollectPresenter<ProjectContract.ProjectModel, ProjectContract.ProjectView>
        implements ProjectContract.ProjectPresenter{

    @Inject
    public ProjectPresenter(ProjectContract.ProjectModel model, ProjectContract.ProjectView view) {
        super(model, view);
    }

    @Override
    public void getProject(int page, int cid) {
        mModel.getProject(page, cid)
                .compose(RxHttpResponseTransformer.compatResult())
                .subscribe(new CommonSubscriber<MainArticleBean>(mView) {
                    @Override
                    public void onNext(MainArticleBean mainArticleBean) {
                        mView.showProject(mainArticleBean);
                    }
                });
    }
}
