package com.yuan.learnproject.base;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.Fragment;

/**
 * @author yuan
 * @date 2019/1/27
 **/
public class BaseMvpPresenter<M, V extends BaseContract.BaseView> {
    protected M mModel;
    protected V mView;
    protected Context mContext;

    public BaseMvpPresenter(M model, V view) {
        mModel = model;
        mView = view;
        initContext();
    }

    private void initContext() {
        if (mView instanceof Fragment) {
            mContext = ((Fragment) mView).getActivity();
        } else {
            mContext = (Activity) mView;
        }
    }

}
