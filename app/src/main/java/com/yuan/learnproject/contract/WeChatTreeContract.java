package com.yuan.learnproject.contract;

import com.yuan.learnproject.base.BaseContract;
import com.yuan.learnproject.bean.BaseBean;
import com.yuan.learnproject.bean.wechat.WeChatChapterBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author yuan
 * @date 2019/4/9
 **/
public interface WeChatTreeContract extends BaseContract {
    interface WeChatTreeView extends BaseView {
        void showResult(List<WeChatChapterBean> weChatChapterBeans);
    }

    interface WeChatTreeModel extends BaseModel {
        Observable<BaseBean<List<WeChatChapterBean>>> getWeChatChapters();
    }

    interface WeChatTreePresenter extends BasePresenter {
        void getWeChatChapters();
    }
}
