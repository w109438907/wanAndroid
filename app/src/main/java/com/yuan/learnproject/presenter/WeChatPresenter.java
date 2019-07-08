package com.yuan.learnproject.presenter;

import com.yuan.learnproject.bean.articles.MainArticleBean;
import com.yuan.learnproject.contract.WeChatContract;
import com.yuan.learnproject.rx.CommonSubscriber;
import com.yuan.learnproject.rx.RxHttpResponseTransformer;

import javax.inject.Inject;

/**
 * @author yuan
 * @date 2019/4/9
 **/
public class WeChatPresenter extends CommonCollectPresenter<WeChatContract.WeChatModel, WeChatContract.WeChatView>
        implements WeChatContract.WeChatPresenter {

    @Inject
    public WeChatPresenter(WeChatContract.WeChatModel model, WeChatContract.WeChatView view) {
        super(model, view);
    }

    @Override
    public void getWeChatArticles(int id, int page) {
        if (isViewAttached()) {
            mModel.getWeChatArticles(id, page)
                    .compose(RxHttpResponseTransformer.compatResult())
                    .subscribe(new CommonSubscriber<MainArticleBean>(mView) {
                        @Override
                        public void onNext(MainArticleBean mainArticleBean) {
                            mView.showResult(mainArticleBean);
                        }
                    });
        }
    }
}
