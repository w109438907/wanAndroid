package com.yuan.learnproject.base;

import android.os.Bundle;

import com.google.gson.Gson;
import com.yuan.learnproject.MainApplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author yuan
 * @date 2019/3/14
 **/
public abstract class BaseActivity extends AppCompatActivity {
    protected Unbinder mUnbinder;
    protected MainApplication mApplication;
    protected Gson mGson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnbinder = ButterKnife.bind(this);
        mApplication = (MainApplication) getApplication();
        mGson = mApplication.getAppComponent().getGson();
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mUnbinder !=Unbinder.EMPTY){
            mUnbinder.unbind();
        }
    }
    protected abstract int getLayout();
    protected abstract void init();
}
