package com.yuan.learnproject.contract;

import com.yuan.learnproject.bean.BaseBean;
import com.yuan.learnproject.bean.articles.MainArticleBean;

import io.reactivex.Observable;

/**
 * @author yuan
 * @date 2019/4/9
 **/
public interface WeChatContract extends CommonCollectContract {
    interface WeChatView extends CommonCollectView {
        void showResult(MainArticleBean mainArticleBean);
    }

    interface WeChatModel extends CommonCollectModel {
        Observable<BaseBean<MainArticleBean>> getWeChatArticles(int id, int page);
    }

    interface WeChatPresenter extends CommonCollectPresenter {
        void getWeChatArticles(int id, int page);
    }
}
