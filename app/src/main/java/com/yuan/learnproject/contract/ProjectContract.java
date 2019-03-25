package com.yuan.learnproject.contract;

import com.yuan.learnproject.bean.BaseBean;
import com.yuan.learnproject.bean.articles.MainArticleBean;

import io.reactivex.Observable;

/**
 * @author yuan
 * @date 2019/3/8
 **/
public interface ProjectContract extends CommonCollectContract {
    interface ProjectView extends CommonCollectView {
        void showProject(MainArticleBean mainArticleBean);
    }

    interface ProjectModel extends CommonCollectModel {
        Observable<BaseBean<MainArticleBean>> getProject(int page, int cid);
    }

    interface ProjectPresenter extends CommonCollectPresenter {
        void getProject(int page, int cid);
    }
}
