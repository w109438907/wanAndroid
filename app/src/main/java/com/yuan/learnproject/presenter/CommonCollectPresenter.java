package com.yuan.learnproject.presenter;

import com.yuan.learnproject.base.BaseMvpPresenter;
import com.yuan.learnproject.bean.BaseBean;
import com.yuan.learnproject.contract.CommonCollectContract;
import com.yuan.learnproject.rx.CommonSubscriber;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author yuan
 * @date 2019/3/3
 **/
public abstract class CommonCollectPresenter<M extends CommonCollectContract.CommonCollectModel, V extends CommonCollectContract.CommonCollectView> extends
        BaseMvpPresenter<M, V> implements CommonCollectContract.CommonCollectPresenter {


    public CommonCollectPresenter(M model, V view) {
        super(model, view);
    }

    @Override
    public void addCollect(int id) {
        mModel.collectArticle(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CommonSubscriber<BaseBean<Object>>(mView) {
                    @Override
                    public void onNext(BaseBean<Object> objectBaseBean) {
                        if (objectBaseBean.getErrorCode() == 0) {
                            mView.collectArticleSuccess();
                        } else {
                            mView.onError(objectBaseBean.getErrorMsg());
                        }
                    }
                });
    }

    @Override
    public void cancelCollect(int id) {
        mModel.cancelCollectArticle(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CommonSubscriber<BaseBean<Object>>(mView) {
                    @Override
                    public void onNext(BaseBean<Object> objectBaseBean) {
                        if (objectBaseBean.getErrorCode() == 0) {
                            mView.cancelCollectArticleSuccess();
                        } else {
                            mView.onError(objectBaseBean.getErrorMsg());
                        }
                    }
                });
    }
}
