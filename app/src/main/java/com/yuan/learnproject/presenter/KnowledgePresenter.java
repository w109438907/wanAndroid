package com.yuan.learnproject.presenter;

import com.yuan.learnproject.bean.articles.MainArticleBean;
import com.yuan.learnproject.contract.KnowledgeContract;
import com.yuan.learnproject.rx.CommonSubscriber;
import com.yuan.learnproject.rx.RxHttpResponseTransformer;

import javax.inject.Inject;

/**
 * @author yuan
 * @date 2019/3/16
 **/
public class KnowledgePresenter extends CommonCollectPresenter<KnowledgeContract.KnowledgeModel, KnowledgeContract.KnowledgeView>
        implements KnowledgeContract.KnowledgePresenter {

    @Inject
    public KnowledgePresenter(KnowledgeContract.KnowledgeModel model, KnowledgeContract.KnowledgeView view) {
        super(model, view);
    }

    @Override
    public void getKnowledgeList(int page, int cid) {
        if (isViewAttached()) {
            mModel.getKnowledgeList(page, cid)
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
