package com.yuan.learnproject.contract;

import com.yuan.learnproject.base.BaseContract;
import com.yuan.learnproject.bean.BaseBean;

import io.reactivex.Observable;

/**
 * @author yuan
 * @date 2019/3/3
 **/
public interface CommonCollectContract extends BaseContract {

    interface CommonCollectView extends BaseView {
        void collectArticleSuccess();
        void cancelCollectArticleSuccess();
    }

    interface CommonCollectModel extends BaseModel {
        Observable<BaseBean<Object>> collectArticle(int articleId);
        Observable<BaseBean<Object>> cancelCollectArticle(int articleId);
    }

    interface CommonCollectPresenter extends BasePresenter {
        void addCollect(int id);
        void cancelCollect(int id);
    }
}
