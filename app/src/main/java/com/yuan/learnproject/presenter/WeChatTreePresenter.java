package com.yuan.learnproject.presenter;

import com.yuan.learnproject.base.BaseMvpPresenter;
import com.yuan.learnproject.bean.wechat.WeChatChapterBean;
import com.yuan.learnproject.contract.WeChatTreeContract;
import com.yuan.learnproject.rx.CommonSubscriber;
import com.yuan.learnproject.rx.RxHttpResponseTransformer;

import java.util.List;

import javax.inject.Inject;

/**
 * @author yuan
 * @date 2019/4/9
 **/
public class WeChatTreePresenter extends BaseMvpPresenter<WeChatTreeContract.WeChatTreeModel, WeChatTreeContract.WeChatTreeView>
        implements WeChatTreeContract.WeChatTreePresenter{

    @Inject
    public WeChatTreePresenter(WeChatTreeContract.WeChatTreeModel model, WeChatTreeContract.WeChatTreeView view) {
        super(model, view);
    }

    @Override
    public void getWeChatChapters() {
        if (isViewAttached()) {
            mModel.getWeChatChapters()
                    .compose(RxHttpResponseTransformer.compatResult())
                    .subscribe(new CommonSubscriber<List<WeChatChapterBean>>(mView) {
                        @Override
                        public void onNext(List<WeChatChapterBean> weChatChapterBeans) {
                            mView.showResult(weChatChapterBeans);
                        }
                    });
        }
    }
}
