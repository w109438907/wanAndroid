package com.yuan.learnproject.model;

import com.yuan.learnproject.api.ApiService;
import com.yuan.learnproject.bean.BaseBean;
import com.yuan.learnproject.bean.wechat.WeChatChapterBean;
import com.yuan.learnproject.contract.WeChatTreeContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author yuan
 * @date 2019/4/9
 **/
public class WeChatTreeModel implements WeChatTreeContract.WeChatTreeModel {
    private ApiService mApiService;

    public WeChatTreeModel(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<List<WeChatChapterBean>>> getWeChatChapters() {
        return mApiService.getWeChatChapters();
    }
}

