package com.yuan.learnproject.rx;

import android.util.Log;

import com.yuan.learnproject.base.BaseContract;
import com.yuan.learnproject.exception.ApiException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author yuan
 * @date 2019/3/2
 **/
public abstract class CommonSubscriber<T> implements Observer<T> {
    private final String TAG = "CommonSubscriber";
    private BaseContract.BaseView mView;

    public CommonSubscriber(BaseContract.BaseView view) {
        mView = view;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ApiException){
            ApiException apiException = (ApiException) e;
            Log.e(TAG, "onError: " + apiException.getErrorMsg() );
            mView.onError(apiException.getErrorMsg());
        }else {
            Log.e(TAG, "onError: " + e.getMessage() );
            mView.onError(e.getMessage());
        }
    }

    @Override
    public void onComplete() {

    }
}
