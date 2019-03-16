package com.yuan.learnproject.contract;

import com.yuan.learnproject.bean.BaseBean;
import com.yuan.learnproject.bean.articles.MainArticleBean;

import io.reactivex.Observable;

/**
 * @author yuan
 * @date 2019/3/16
 **/
public interface KnowledgeContract extends CommonCollectContract {
    interface KnowledgeView extends CommonCollectView {
        void showResult(MainArticleBean mainArticleBean);
    }

    interface KnowledgeModel extends CommonCollectModel {
        Observable<BaseBean<MainArticleBean>> getKnowledgeList(int page, int cid);
    }

    interface KnowledgePresenter extends CommonCollectPresenter {
        void getKnowledgeList(int page, int cid);
    }
}
