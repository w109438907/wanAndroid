package com.yuan.learnproject.contract;

import com.yuan.learnproject.bean.BaseBean;
import com.yuan.learnproject.bean.articles.MainArticleBean;
import com.yuan.learnproject.bean.articles.MainArticleDataBean;
import com.yuan.learnproject.bean.articles.MainBannerBean;

import java.util.List;

import io.reactivex.Observable;

public interface MainArticleContract extends CommonCollectContract {
    interface MainArticleView extends CommonCollectView {
        void showResult(MainArticleBean mainArticleBean);
        void showBanner(List<MainBannerBean> mainBannerBean);
        void getBannerError();
    }

    interface MainArticleModel extends CommonCollectModel {
        Observable<BaseBean<MainArticleBean>> getMainArticle(int page);

        Observable<BaseBean<List<MainArticleDataBean>>> getTopArticles();

        Observable<BaseBean<List<MainBannerBean>>> getBanner();
    }

    interface MainArticlePresenter extends CommonCollectPresenter {
        void requestData(int page);
        void requestBanner();
        void requestHomeData();
    }
}
