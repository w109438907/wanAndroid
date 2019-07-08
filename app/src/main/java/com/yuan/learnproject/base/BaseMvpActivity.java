package com.yuan.learnproject.base;

import android.os.Bundle;

import com.google.gson.Gson;
import com.yuan.learnproject.MainApplication;
import com.yuan.learnproject.di.component.AppComponent;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author yuan
 * @date 2019/1/27
 **/
public abstract class BaseMvpActivity <T extends BaseMvpPresenter> extends AppCompatActivity implements BaseContract.BaseView {
    private Unbinder mUnbinder;
    protected MainApplication mApplication;
    protected Gson mGson;

    @Inject
    public T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnbinder = ButterKnife.bind(this);
        mApplication = (MainApplication) getApplication();
        setupActivityComponent(mApplication.getAppComponent());
        mGson = mApplication.getAppComponent().getGson();
        init();
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
        if(mUnbinder !=Unbinder.EMPTY){
            mUnbinder.unbind();
        }
    }

    protected abstract int getLayout();
    protected abstract void init();

    /**
     * 通用初始化 Dagger 方法
     */
    protected abstract void setupActivityComponent(AppComponent appComponent);
}
