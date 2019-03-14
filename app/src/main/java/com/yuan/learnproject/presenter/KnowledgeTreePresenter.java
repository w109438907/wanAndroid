package com.yuan.learnproject.presenter;

import com.yuan.learnproject.base.BaseMvpPresenter;
import com.yuan.learnproject.bean.knowledge.TreesBean;
import com.yuan.learnproject.contract.KnowledgeTreeContract;
import com.yuan.learnproject.rx.CommonSubscriber;
import com.yuan.learnproject.rx.RxHttpResponseTransformer;

import java.util.List;

import javax.inject.Inject;

/**
 * @author yuan
 * @date 2019/3/8
 **/
public class KnowledgeTreePresenter extends BaseMvpPresenter<KnowledgeTreeContract.KnowledgeModel, KnowledgeTreeContract.KnowledgeView>
        implements KnowledgeTreeContract.KnowledgePresenter{

    @Inject
    public KnowledgeTreePresenter(KnowledgeTreeContract.KnowledgeModel model, KnowledgeTreeContract.KnowledgeView view) {
        super(model, view);
    }

    @Override
    public void getKnowledgeData() {
        mModel.getKnowledgeTree()
                .compose(RxHttpResponseTransformer.compatResult())
                .subscribe(new CommonSubscriber<List<TreesBean>>(mView) {
                    @Override
                    public void onNext(List<TreesBean> treesBeans) {
                        mView.showTreeData(treesBeans);
                    }
                });
    }
}
