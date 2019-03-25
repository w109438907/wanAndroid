package com.yuan.learnproject.contract;

import com.yuan.learnproject.base.BaseContract;
import com.yuan.learnproject.bean.BaseBean;
import com.yuan.learnproject.bean.project.ProjectTreeBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author yuan
 * @date 2019/3/22
 **/
public interface ProjectTreeContract extends BaseContract {
    interface ProjectTreeView extends BaseView {
        void showProjectTree(List<ProjectTreeBean> projectTreeBeanList);
    }

    interface ProjectTreeModel extends BaseModel {
        Observable<BaseBean<List<ProjectTreeBean>>> getProjectTree();
    }

    interface ProjectTreePresenter extends BasePresenter {
        void getProjectTree();
    }
}
