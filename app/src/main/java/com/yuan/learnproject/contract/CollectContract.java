package com.yuan.learnproject.contract;

import com.yuan.learnproject.base.BaseContract;
import com.yuan.learnproject.bean.BaseBean;
import com.yuan.learnproject.bean.articles.CollectionArticleBean;

import io.reactivex.Observable;

/**
 * @author yuan
 * @date 2019/3/22
 **/
public interface CollectContract extends BaseContract {
    interface CollectView extends BaseView {
        void showCollect(CollectionArticleBean collectionArticleBean);
        void onDeleteCollectionSuccess(int position);
    }

    interface CollectModel extends BaseModel {
        Observable<BaseBean<CollectionArticleBean>> getCollectArticles(int pageIndex);
        Observable<BaseBean<Object>> deleteCollectArticle(int articleId, int originId);
    }

    interface CollectPresenter extends BasePresenter {
        void getCollectArticles(int pageIndex);
        void deleteCollectArticle(int position, int articleId, int originId);
    }
}
