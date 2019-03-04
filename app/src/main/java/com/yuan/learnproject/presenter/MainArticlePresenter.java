package com.yuan.learnproject.presenter;

import com.yuan.learnproject.base.BaseMvpPresenter;
import com.yuan.learnproject.bean.BaseBean;
import com.yuan.learnproject.bean.articles.MainArticleBean;
import com.yuan.learnproject.bean.articles.MainArticleDataBean;
import com.yuan.learnproject.bean.articles.MainBannerBean;
import com.yuan.learnproject.contract.MainArticleContract;
import com.yuan.learnproject.rx.CommonSubscriber;
import com.yuan.learnproject.rx.RxHttpResponseTransformer;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

public class MainArticlePresenter extends CommonCollectPresenter
        <MainArticleContract.MainArticleModel, MainArticleContract.MainArticleView>
        implements MainArticleContract.MainArticlePresenter{

    @Inject
    public MainArticlePresenter(MainArticleContract.MainArticleModel model, MainArticleContract.MainArticleView view) {
        super(model, view);
    }

    @Override
    public void requestData(int page) {
        mModel.getMainArticle(page)
                .compose(RxHttpResponseTransformer.compatResult())
                .subscribe(new CommonSubscriber<MainArticleBean>(mView) {
                    @Override
                    public void onNext(MainArticleBean mainArticleBean) {
                        mView.showResult(mainArticleBean);
                    }
                });
    }

    @Override
    public void requestBanner() {
        mModel.getBanner()
                .compose(RxHttpResponseTransformer.compatResult())
                .subscribe(new CommonSubscriber<List<MainBannerBean>>(mView) {
                    @Override
                    public void onNext(List<MainBannerBean> mainBannerBeans) {
                        mView.showBanner(mainBannerBeans);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.getBannerError();
                    }
                });

    }

    @Override
    public void requestHomeData() {
        Observable.zip(mModel.getTopArticles(), mModel.getMainArticle(0),
                new BiFunction<BaseBean<List<MainArticleDataBean>>, BaseBean<MainArticleBean>, BaseBean<MainArticleBean>>() {
                    @Override
                    public BaseBean<MainArticleBean> apply(BaseBean<List<MainArticleDataBean>> listBaseBean, BaseBean<MainArticleBean> mainArticleBeanBaseBean) throws Exception {
                        for (MainArticleDataBean data : listBaseBean.getData()) {
                            data.setTop(1);
                        }
                        mainArticleBeanBaseBean.getData().getDatas().addAll(0, listBaseBean.getData());
                        return mainArticleBeanBaseBean;
                    }
                })
                .compose(RxHttpResponseTransformer.compatResult())
                .subscribe(new CommonSubscriber<MainArticleBean>(mView) {
                    @Override
                    public void onNext(MainArticleBean mainArticleBean) {
                        mView.showResult(mainArticleBean);
                    }
                });
    }
}
