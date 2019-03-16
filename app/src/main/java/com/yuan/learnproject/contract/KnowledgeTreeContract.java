package com.yuan.learnproject.contract;

import com.yuan.learnproject.base.BaseContract;
import com.yuan.learnproject.bean.BaseBean;
import com.yuan.learnproject.bean.knowledge.TreesBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author yuan
 * @date 2019/3/8
 **/
public interface KnowledgeTreeContract extends BaseContract {
    interface KnowledgeTreeView extends BaseView {
        void showTreeData(List<TreesBean> treesBeans);
    }

    interface KnowledgeTreeModel extends BaseModel {
        Observable<BaseBean<List<TreesBean>>> getKnowledgeTree();
    }

    interface KnowledgeTreePresenter extends BasePresenter {
        void getKnowledgeData();
    }
}