package com.yuan.learnproject.ui.activity;

import com.yuan.learnproject.R;
import com.yuan.learnproject.base.BaseActivity;
import com.yuan.learnproject.contract.MainContract;
import com.yuan.learnproject.di.component.AppComponent;
import com.yuan.learnproject.di.component.DaggerMainActivityComponent;
import com.yuan.learnproject.di.module.MainActivityModule;
import com.yuan.learnproject.presenter.MainPresenter;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.MainView{

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainActivityComponent.builder()
                .appComponent(appComponent)
                .mainActivityModule(new MainActivityModule(this))
                .build()
                .inject(this);
    }
}
