package com.yuan.learnproject.presenter;

import com.yuan.learnproject.base.BaseMvpPresenter;
import com.yuan.learnproject.bean.BaseBean;
import com.yuan.learnproject.bean.articles.CollectionArticleBean;
import com.yuan.learnproject.contract.CollectContract;
import com.yuan.learnproject.rx.CommonSubscriber;
import com.yuan.learnproject.rx.RxHttpResponseTransformer;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author yuan
 * @date 2019/4/3
 **/
public class CollectPresenter extends BaseMvpPresenter<CollectContract.CollectModel, CollectContract.CollectView>
        implements CollectContract.CollectPresenter {

    @Inject
    public CollectPresenter(CollectContract.CollectModel model, CollectContract.CollectView view) {
        super(model, view);
    }

    @Override
    public void getCollectArticles(int pageIndex) {
        if (isViewAttached()) {
            mModel.getCollectArticles(pageIndex)
                    .compose(RxHttpResponseTransformer.compatResult())
                    .subscribe(new CommonSubscriber<CollectionArticleBean>(mView) {
                        @Override
                        public void onNext(CollectionArticleBean collectionArticleBeans) {
                            mView.showCollect(collectionArticleBeans);
                        }
                    });
        }
    }

    @Override
    public void deleteCollectArticle(int position, int articleId, int originId) {
        if (isViewAttached()) {
            mModel.deleteCollectArticle(articleId, originId)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new CommonSubscriber<BaseBean<Object>>(mView) {
                        @Override
                        public void onNext(BaseBean<Object> objectBaseBean) {
                            if (objectBaseBean.getErrorCode() == 0) {
                                mView.onDeleteCollectionSuccess(position);
                            } else {
                                mView.onError(objectBaseBean.getErrorMsg());
                            }
                        }
                    });
        }
    }
}
