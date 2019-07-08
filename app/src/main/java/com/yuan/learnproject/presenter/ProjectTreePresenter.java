package com.yuan.learnproject.presenter;

import com.yuan.learnproject.base.BaseMvpPresenter;
import com.yuan.learnproject.bean.project.ProjectTreeBean;
import com.yuan.learnproject.contract.ProjectTreeContract;
import com.yuan.learnproject.rx.CommonSubscriber;
import com.yuan.learnproject.rx.RxHttpResponseTransformer;

import java.util.List;

import javax.inject.Inject;

/**
 * @author yuan
 * @date 2019/3/22
 **/
public class ProjectTreePresenter extends BaseMvpPresenter<ProjectTreeContract.ProjectTreeModel, ProjectTreeContract.ProjectTreeView>
        implements ProjectTreeContract.ProjectTreePresenter{

    @Inject
    public ProjectTreePresenter(ProjectTreeContract.ProjectTreeModel model, ProjectTreeContract.ProjectTreeView view) {
        super(model, view);
    }

    @Override
    public void getProjectTree() {
        if (isViewAttached()) {
            mModel.getProjectTree()
                    .compose(RxHttpResponseTransformer.compatResult())
                    .subscribe(new CommonSubscriber<List<ProjectTreeBean>>(mView) {
                        @Override
                        public void onNext(List<ProjectTreeBean> projectTreeBeans) {
                            mView.showProjectTree(projectTreeBeans);
                        }
                    });
        }
    }
}
